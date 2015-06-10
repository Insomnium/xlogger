package net.ins.xlogger.util;

import net.ins.xlogger.auth.UserDetailsImpl;
import net.ins.xlogger.user.domain.Role;
import net.ins.xlogger.user.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ins on 3/3/15.
 */
public class AuthUtil {
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    public static User getUserDetails() {
        if (!isAuthenticated()) {
            return null;
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsImpl) principal).getUser();
    }

    public static boolean isAdmin() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Collection<? extends GrantedAuthority>> optGrantedAuthorities = Optional
                .ofNullable(principal)
                .map(UserDetailsImpl::getAuthorities);

        return !optGrantedAuthorities.orElse(Collections.emptyList())
                .stream()
                .noneMatch(auth -> Role.ADMIN == Role.getRoleBySysName(auth.getAuthority()));

    }

    public static long getUserId() {
        User user = getUserDetails();
        return user != null ? user.getId() : -1;
    }

    public static Properties getCookies(Cookie[] cookies) {
        Properties p = new Properties();
        if (cookies == null) {
            return p;
        }

        for (Cookie cookie : cookies) {
            p.put(cookie.getName(), cookie.getValue());
        }

        return p;
    }

    public static String logIp(HttpServletRequest request) {
        String msg = " [IP:" + request.getRemoteAddr();
        ArrayList<String> xff = Collections.list(request.getHeaders("X-Forwarded-For"));

        if (!xff.isEmpty()) {
            msg = msg + " XFF:" + StringUtils.join(xff, ",");
        }

        return msg + "]";
    }
}
