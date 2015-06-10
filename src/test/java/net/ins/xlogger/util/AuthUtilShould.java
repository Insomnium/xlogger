package net.ins.xlogger.util;

import org.junit.Test;

/**
 * Created by ins on 4/2/15.
 */
public class AuthUtilShould {

    @Test
    public void returnFalseIfUserIsNull() {
        boolean admin = AuthUtil.isAdmin();

//        return admin;
    }
}
