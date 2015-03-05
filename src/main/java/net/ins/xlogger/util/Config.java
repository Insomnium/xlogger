package net.ins.xlogger.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by ins on 3/3/15.
 */
@Service("config")
public class Config {

    @Qualifier("properties")
    @Autowired
    private Properties properties;
    private String mainURI;
    private String secureUrl;
    private int csrfCookieExpirationTime;

    @PostConstruct
    private void init() {
        try {
            mainURI = properties.getProperty("mainUri");
            csrfCookieExpirationTime = Integer.parseInt(properties.getProperty("csrfCookieExpirationTime"));
            secureUrl = properties.getProperty("secureUrl");
        } catch (Exception e) {
            throw new RuntimeException("Error occurred reading application configuration");
        }
    }

    public String getMainURI() {
        return mainURI;
    }

    public int getCsrfCookieExpirationTime() {
        return csrfCookieExpirationTime;
    }

    public String getSecureUrl() {
        return secureUrl;
    }
}
