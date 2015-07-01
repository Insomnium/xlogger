package net.ins.xlogger.msg.dao.impl;

import net.ins.xlogger.exceptions.MessageDaoException;
import net.ins.xlogger.msg.dao.TopicDao;
import net.ins.xlogger.msg.entities.Topic;
import net.ins.xlogger.user.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/7/15.
 */
@Repository
public class TopicDaoImpl implements TopicDao {

    private static final Logger logger = Logger.getLogger(TopicDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Topic getTopicById(long id) throws MessageDaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return (Topic) session.get(Topic.class, id);
        } catch (Exception e) {
            logger.error("Error occurred while getting topic: " + id);
            throw new MessageDaoException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Topic> listUserTopics(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Topic.class);

        if (searchDepth != null) {
            criteria.add(Restrictions.le("postDate", searchDepth));
        }

        if (page != null && limit != null) {
            criteria.setFirstResult(page * limit).setMaxResults(limit);
        }

        criteria.addOrder(Order.desc("postDate"));

        return criteria.list();
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public long createTopic(Topic topic, Long authorId) throws MessageDaoException {
        Session session = sessionFactory.openSession();
        try {
            topic.setAuthor((User) session.byId(User.class).getReference(authorId));
            session.save(topic); // save() inserts record immediately. persist() method is delayed. TODO: figure out why. First lvl cache?
            return topic.getId();
        } catch (Exception e) {
            logger.error("Error occurred while saving topic: " + topic.toString(), e);
            throw new MessageDaoException(e);
        } finally {
            session.close();
        }
    }
}
