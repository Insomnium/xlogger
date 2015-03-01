package net.ins.xlogger.user.dao.impl;

import net.ins.xlogger.user.User;
import net.ins.xlogger.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ins on 3/1/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final String SELECT_BY_LOGIN = "select user_id from users where login = ?";
    private static final String SELECT = "select * from users where user_id = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(String login) {
        Long userId = jdbcTemplate.queryForObject(SELECT_BY_LOGIN, Long.class, login);
        return getUser(userId);
    }

    @CachePut("Users")
    @Override
    public User getUser(long id) {
        return getUserInternal(id);
    }

    @Cacheable("Users")
    public User getUserCached(long id) {
        return getUserInternal(id);
    }

    private User getUserInternal(long id) {
        return jdbcTemplate.queryForObject(SELECT, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet);
            }
        }, id);
    }
}
