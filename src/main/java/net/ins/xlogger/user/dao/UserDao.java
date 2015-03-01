package net.ins.xlogger.user.dao;

import net.ins.xlogger.user.User;

/**
 * Created by ins on 3/1/15.
 */
public interface UserDao {
    User getUser(String login);
    User getUser(long id);
}
