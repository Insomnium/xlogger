package net.ins.xlogger.wall;

import net.ins.xlogger.exceptions.DaoException;
import net.ins.xlogger.msg.MessageRequest;
import net.ins.xlogger.msg.domain.PreparedTopic;
import net.ins.xlogger.msg.service.ContentService;
import net.ins.xlogger.util.AuthUtil;
import net.ins.xlogger.util.UserConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ins on 3/4/15.
 */
@Controller
@RequestMapping("/wall")
public class WallController {

    private static final Logger logger = Logger.getLogger(WallController.class);

    @Autowired
    private ContentService contentService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView wallForm(
            ModelMap model,
            ServletRequest request,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "limit", required = false) Integer limit) {

        model.put("config", UserConfig.getConfig(request));
        Integer lim = limit != null ? limit : UserConfig.getConfig(request).getWallSize();
        List<PreparedTopic> topics = new ArrayList<>();
        try {
            if (AuthUtil.getUserId() >= 0) {
                topics = contentService.listTopics(AuthUtil.getUserId(), null, page, lim);
            } else {
                // TODO: anonymous branch
            }
        } catch (DaoException e) {
            logger.error("Error occurred while getting wall messages", e);
        }

        model.put("form", new MessageRequest());
        return new ModelAndView("wall", "topics", topics);
    }
}
