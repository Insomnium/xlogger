package net.ins.xlogger.wall;

import net.ins.xlogger.util.UserConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

/**
 * Created by ins on 3/4/15.
 */
@Controller
public class SampleController {

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public ModelAndView sampleForm(ModelMap model, ServletRequest request) {
        model.put("config", UserConfig.getConfig(request));
        return new ModelAndView("sample");
    }
}
