package net.ins.xlogger.user;

import net.ins.xlogger.user.dao.UserDao;
import net.ins.xlogger.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ins on 3/1/15.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = { "application/json" })
    @ResponseBody
    public User getUserByLogin(@RequestParam(value = "login", required = false, defaultValue = "insomnium") String login) {
        return userDao.getUser(login);
    }
}
