package net.ins.xlogger.user.service.impl;

import net.ins.xlogger.user.dao.UserDao;
import net.ins.xlogger.user.domain.PreparedUser;
import net.ins.xlogger.user.domain.Role;
import net.ins.xlogger.user.entities.User;
import net.ins.xlogger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ins on 3/30/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PreparedUser> listUsers() {
        List<User> users = userDao.listUsers();
        return users.stream()
                .map(u -> new PreparedUser(u.getLogin(),
                        u.getEmail(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getAvatarUrl(),
                        u.isBlocked(),
                        u.isActivated(),
                        u.getLastLogin(),
                        u.getRoles().stream()
                                .map(r -> Role.getRoleBySysName(r.getRoleSysName())).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
}
