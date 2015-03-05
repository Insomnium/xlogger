package net.ins.xlogger.auth;

import net.ins.xlogger.user.dao.UserDao;
import net.ins.xlogger.util.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by ins on 3/4/15.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView performRegistration(
            HttpServletRequest request,
            @Valid @ModelAttribute("form") RegistrationRequest form,
            Errors errors,
            Locale locale) {

        HttpSession session = request.getSession();

        if (errors.hasErrors()) {
            return new ModelAndView("register");
        }

        if (session.getAttribute("reg-page-visited") == null) {
            logger.warn("Anti-flood protection. IP: " + request.getRemoteAddr());
            errors.reject(null, messageSource.getMessage("error.unknown.trylater", null, locale));
        }

        if (userDao.checkEmailExists(form.getEmail())) {
            errors.rejectValue("email", null, messageSource.getMessage("exception.registration.emailexists", null, locale));
        }

        if (userDao.getUser(form.getLogin()) != null) {
            errors.rejectValue("login", null, messageSource.getMessage("exception.registration.loginexists.regRequest.login",
                    null, locale));
        }

        if (errors.hasErrors()) {
            return new ModelAndView("register");
        }

        userDao.create(form.getLogin(), new BCryptPasswordEncoder().encode(form.getPassword()), form.getEmail(),
                form.getFirstName(), form.getLastName());
        logger.info("User registered: " + form.getLogin() + AuthUtil.logIp(request));
        // TODO: send confirmation email
        return new ModelAndView("done", "message", messageSource.getMessage("view.signup.done.awaitconfirmation",
                null, locale));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationForm() {
        return new ModelAndView("register", "form", new RegistrationRequest());
    }
}
