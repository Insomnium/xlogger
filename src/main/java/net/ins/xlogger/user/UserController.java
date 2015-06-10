package net.ins.xlogger.user;

import net.ins.xlogger.user.dao.UserDao;
import net.ins.xlogger.user.entities.User;
import net.ins.xlogger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ins on 3/1/15.
 */
@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public User getUserByLogin(@RequestParam(value = "login", required = false, defaultValue = "insomnium") String login) {
        return userDao.getUser(login);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listUsers(ModelMap model) {
        return new ModelAndView("userlist", "users", userService.listUsers());
    }
}
