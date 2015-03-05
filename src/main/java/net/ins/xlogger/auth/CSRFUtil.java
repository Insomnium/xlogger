package net.ins.xlogger.auth;

import net.ins.xlogger.util.Config;
import org.postgresql.util.Base64;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

/**
 * Created by ins on 3/3/15.
 */
public class CSRFUtil {
    public static final String CSRF_TOKEN = "CSRF_TOKEN";
    public static final String CSRF_TOKEN_ATTRIBUTE = "csrfToken";
    public static final String CSRF_HIDDEN_INPUT = "csrf";

    private static int expiryTime = -1;

    public static void generateCSRFToken(HttpServletRequest request, HttpServletResponse response) {
        SecureRandom rnd = new SecureRandom();
        byte[] tokenBytes = new byte[16];
        rnd.nextBytes(tokenBytes);

        String token = Base64.encodeBytes(tokenBytes);
        Cookie cookie = new Cookie(CSRF_TOKEN, token);
        if (expiryTime < 0) {
            WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            Config config = appCtx.getBean(Config.class);
            expiryTime = config.getCsrfCookieExpirationTime();
        }
        cookie.setMaxAge(expiryTime);
        cookie.setPath("/");
        response.addCookie(cookie);

        request.setAttribute(CSRF_TOKEN_ATTRIBUTE, token);
    }
}
