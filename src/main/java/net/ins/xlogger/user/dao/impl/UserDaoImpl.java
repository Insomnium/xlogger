package net.ins.xlogger.user.dao.impl;

import net.ins.xlogger.user.dao.UserDao;
import net.ins.xlogger.user.entities.Role;
import net.ins.xlogger.user.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ins on 3/1/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getUser(String login) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("user.selectByLogin")
                .setParameter("login", login);

        return (User) query
                .uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public boolean checkEmailExists(String email) {
        Session session = sessionFactory.openSession();
        try {
            return ((Number) session.getNamedQuery("user.countByEmail")
                    .setParameter("email", email)
                    .uniqueResult())
                    .intValue() > 0;
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Method must be transactional to persist record into link table
    public long create(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            user.setRoles(new HashSet<Role>());
            user.getRoles().add((Role) session.byId(Role.class).getReference(net.ins.xlogger.user.Role.USER.getRoleId()));
            session.save(user);
        } catch (Exception e) {
            logger.error("Error occurred while creating user", e);
        }

        return user.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Both createUser methods are transactional because @Transactional is skipped during same-class method invokations
    public long create(String login, String password, String email, String firstName, String lastName) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBlocked(false);

        return create(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(session);
        sessionFactory.getCache().evictEntity(User.class, user);

        // FIXME !!!!! HIGHLY IMPORTANT !!!!! CHECK EVICTING SAME NAMED _QUERY_ CACHE DOES NOT EVICT THE ENTIRE REGION !!!!!!
        sessionFactory.getCache().evictQueryRegion("Users");
    }

//    @CachePut("Users")
//    @Override
//    public User getUser(long id) {
//        return getUserInternal(id);
//    }
//
//    @Cacheable("Users")
//    public User getUserCached(long id) {
//        return getUserInternal(id);
//    }

//    private User getUserInternal(long id) {
//        return jdbcTemplate.queryForObject(SELECT, new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                return new User(resultSet);
//            }
//        }, id);
//    }
}
