package net.ins.xlogger.util;

import net.ins.xlogger.user.entities.User;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;

/**
 * Created by ins on 3/3/15.
 */
public class UserConfig {
    private Config config;
    private User userDetails;

    public UserConfig(WebApplicationContext appCtx) {
        this.config = (Config) appCtx.getBean("config");
        this.userDetails = AuthUtil.getUserDetails();
    }

    public UserConfig(ServletRequest request) {
        this(WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()));
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public static UserConfig getConfig(ServletRequest request) {
        return new UserConfig(request);
    }

    public String getSecureUrl() {
        return this.config.getSecureUrl();
    }

    public String getMainUrl() {
        return this.config.getMainURI();
    }

    public String getUserLogin() {
        return this.userDetails.getLogin();
    }

    public boolean isAuthenticated() {
        return this.userDetails != null;
    }

    public int getWallSize() {
        return this.config.getWallPageSize();
    }
}
