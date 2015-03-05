package net.ins.xlogger.auth;

import net.ins.xlogger.util.AuthUtil;
import net.ins.xlogger.util.Config;
import net.ins.xlogger.util.UserConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ins on 3/3/15.
 */
public class SecurityFilter extends GenericFilterBean implements InitializingBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        WebApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setAttribute("config", appCtx.getBean(Config.class));
        request.setAttribute("user", new UserConfig(appCtx));
        request.setCharacterEncoding("utf-8");

        Properties cookies = AuthUtil.getCookies(request.getCookies());
        if (cookies.get(CSRFUtil.CSRF_TOKEN) == null) {
            CSRFUtil.generateCSRFToken(request, response);
        } else {
            request.setAttribute(CSRFUtil.CSRF_TOKEN_ATTRIBUTE, cookies.getProperty(CSRFUtil.CSRF_TOKEN).trim());
        }

        response.addHeader("Cache-Control", "private");
    }
}
