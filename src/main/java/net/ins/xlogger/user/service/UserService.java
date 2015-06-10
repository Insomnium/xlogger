package net.ins.xlogger.user.service;

import net.ins.xlogger.user.domain.PreparedUser;

import java.util.List;

/**
 * Created by ins on 3/30/15.
 */
public interface UserService {
    List<PreparedUser> listUsers();
}
