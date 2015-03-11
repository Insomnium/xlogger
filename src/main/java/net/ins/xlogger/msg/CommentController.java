package net.ins.xlogger.msg;

import net.ins.xlogger.common.CommentServiceException;
import net.ins.xlogger.msg.service.CommentService;
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
    private CommentService commentService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/{id}/add", method = RequestMethod.POST)
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
            commentService.addComment(topicId, userId, form);
        } catch (CommentServiceException e) {
            errors.reject(null, messageSource.getMessage("error.unknown.trylater", null, locale));
            logger.error("Error occurred while adding comment from wall. Topic id: " + topicId + "; userId: " + userId);
        }

        return new ModelAndView("wall");
    }
}
