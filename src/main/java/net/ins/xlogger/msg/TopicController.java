package net.ins.xlogger.msg;

import net.ins.xlogger.common.MessageDaoException;
import net.ins.xlogger.msg.dao.MessageDao;
import net.ins.xlogger.msg.dao.TopicDao;
import net.ins.xlogger.msg.entities.Topic;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ins on 3/7/15.
 */
@Controller
@RequestMapping("/topic")
public class TopicController {

    private static final Logger logger = Logger.getLogger(TopicController.class);

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addMessageForm(ModelMap model, ServletRequest request) {
        return new ModelAndView("add-topic", "form", new TopicRequest());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView topicForm(
            ModelMap model,
            ServletRequest request,
            @PathVariable("id") Integer id) {

        try {
            model.put("topic", topicDao.getTopicById(id));
            model.put("messages", messageDao.listTopicMessages(id));
            model.put("form", new MessageRequest());
        } catch (MessageDaoException e) {
            logger.error("Error occurred while getting topic: " + id);
        }

        return new ModelAndView("topic");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView performAddingTopic(
            ServletRequest request,
            @Valid @ModelAttribute("form") TopicRequest form,
            Errors errors,
            Locale locale) {

        Topic topic = new Topic();

        if (errors.hasErrors()) {
            return new ModelAndView("add-topic");
        }

        BeanUtils.copyProperties(form, topic);
        topic.setPostDate(new Date());
        if (topic.getMarkupType() == null) {
            topic.setMarkupType(MarkupType.PLAIN);
        }
        try {
            topicDao.createTopic(topic);
        } catch (MessageDaoException e) {
            logger.error("Error occurred while adding topic", e);
            errors.reject(null, messageSource.getMessage("error.unknown.trylater", null, locale));
            return new ModelAndView("add-topic");
        }

        return new ModelAndView(new RedirectView("/wall"));
    }

//    @RequestMapping(value = "/comment", method = RequestMethod.GET)
//    public ModelAndView commentForm(
//            ModelMap model,
//
//
//    ) {
//
//    }
}
