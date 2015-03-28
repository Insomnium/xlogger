package net.ins.xlogger.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;

/**
 * Created by ins on 3/3/15.
 */
@Controller
public class LoginController {

    @Qualifier("authenticationManager")
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private UserServiceImpl userService;

//    @RequestMapping(value = "/perform_login", method = RequestMethod.POST)
//    public ModelAndView performLogin(
//            @RequestParam(value = "login", required = true) final String login,
//            @RequestParam(value = "passwd", required = true) final String passwd) {
//
//        try {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, passwd);
//            UserDetailsImpl userDetails = (UserDetailsImpl) userService.loadUserByUsername(login);
//            token.setDetails(userDetails);
//            Authentication auth = authenticationManager.authenticate(token);
//            UserDetailsImpl details = (UserDetailsImpl) auth.getDetails();
//
//            if (!details.getUser().isActivated()) {
//                throw new AuthenticationServiceException("Access violated. User is not activated");
//            }
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            // TODO: rememberMe service
//
//        } catch (Exception e) {
//            return new ModelAndView(new RedirectView("/login.jsp?error=true"));
//        }
//
//        return new ModelAndView(new RedirectView("/"));
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm(ModelMap modelMap, ServletRequest request) {
//        modelMap.put("config", UserConfig.getConfig(request));
        return new ModelAndView("login"); // WEB-INF/pages/login.jsp
    }


}
