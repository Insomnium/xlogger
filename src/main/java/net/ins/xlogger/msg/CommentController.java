package net.ins.xlogger.msg;

import net.ins.xlogger.exceptions.CommentServiceException;
import net.ins.xlogger.msg.service.ContentService;
import net.ins.xlogger.util.AuthUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by ins on 3/8/15.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = Logger.getLogger(CommentController.class);

    @Autowired
    private ContentService contentService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ModelAndView commentFrom(
            @PathVariable("id") Long topicId,
            @ModelAttribute("form") @Valid MessageRequest form,
            Errors errors,
            Locale locale) {

        if (errors.hasErrors()) {
            return new ModelAndView("wall");
        }

        long userId = AuthUtil.getUserId();
        try {
            contentService.addComment(topicId, userId, form);
        } catch (CommentServiceException e) {
            errors.reject(null, messageSource.getMessage("error.unknown.trylater", null, locale));
            logger.error("Error occurred while adding comment from wall. Topic id: " + topicId + "; userId: " + userId);
        }

        if (topicId != null) {
            return new ModelAndView(new RedirectView("/topic/" + topicId));
        }
        return new ModelAndView(new RedirectView("/wall"));
    }
}
