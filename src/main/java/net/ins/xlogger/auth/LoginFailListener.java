package net.ins.xlogger.auth;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

/**
 * Created by ins on 3/4/15.
 */
@Component
public class LoginFailListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    private Logger logger = Logger.getLogger(LoginFailListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent abstractAuthenticationFailureEvent) {
        logger.error("User login failed: " + abstractAuthenticationFailureEvent.getException().getMessage());
    }
}
