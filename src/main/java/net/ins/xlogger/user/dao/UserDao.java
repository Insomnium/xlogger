package net.ins.xlogger.user.dao;

import net.ins.xlogger.user.entities.User;

import java.util.List;

/**
 * Created by ins on 3/1/15.
 */
public interface UserDao {
    User getUser(String login);
    List<User> listUsers();
    boolean checkEmailExists(String email);
    long create(User user);
    long create(String login, String password, String email, String firstName, String lastName);
    void update(User user);
}
