package net.ins.xlogger.wall;

import net.ins.xlogger.common.CommentServiceException;
import net.ins.xlogger.common.DaoException;
import net.ins.xlogger.msg.MessageRequest;
import net.ins.xlogger.msg.dao.TopicDao;
import net.ins.xlogger.msg.entities.Topic;
import net.ins.xlogger.msg.service.CommentService;
import net.ins.xlogger.util.AuthUtil;
import net.ins.xlogger.util.Config;
import net.ins.xlogger.util.UserConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ins on 3/4/15.
 */
@Controller
@RequestMapping("/wall")
public class WallController {

    private static final Logger logger = Logger.getLogger(WallController.class);

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private Config config;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView wallForm(
            ModelMap model,
            ServletRequest request,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = false) Integer limit) {

        model.put("config", UserConfig.getConfig(request));
        Integer lim = limit != null ? limit : UserConfig.getConfig(request).getWallSize();
        List<Topic> topics = new ArrayList<Topic>();
        try {
            if (AuthUtil.getUserId() >= 0) {
                topics = topicDao.listUserTopics(AuthUtil.getUserId(), null, page, lim);
            } else {
                // TODO: anonymous branch
            }
        } catch (DaoException e) {
            logger.error("Error occurred while getting wall messages", e);
        }
        for (Topic topic : topics) {
            topic.setUrl(config.getMainURI() + "/topic/" + topic.getId());
        }

        model.put("form", new MessageRequest());
        return new ModelAndView("wall", "topics", topics);
    }
}
