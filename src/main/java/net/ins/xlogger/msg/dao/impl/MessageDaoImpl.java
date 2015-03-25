package net.ins.xlogger.msg.dao.impl;

import net.ins.xlogger.common.MessageDaoException;
import net.ins.xlogger.msg.dao.MessageDao;
import net.ins.xlogger.msg.entities.Message;
import net.ins.xlogger.msg.entities.Topic;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/6/15.
 */
@Repository
public class MessageDaoImpl implements MessageDao {

    private static final Logger logger = Logger.getLogger(MessageDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public List<Message> listUserMessages(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Message.class);
            if (searchDepth != null) {
                criteria.add(Restrictions.le("postDate", searchDepth));
            }

            if (page != null && limit != null) {
                criteria.setFirstResult(page * limit).setMaxResults(limit);
            }

            return criteria.list();
        } catch (Exception e) {
            logger.error("Error occurred while obtaining message list for user: " + userId, e);
            throw new MessageDaoException(e);
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public List<Message> listTopicMessages(long topicId) throws MessageDaoException {
        Session session = sessionFactory.openSession();
        try {
            return session.getNamedQuery("message.getByTopicId")
                    .setParameter("topicId", topicId)
                    .list();
        } catch (Exception e) {
            logger.error("Error occurred while getting messages related to topic: " + topicId);
            throw new MessageDaoException(e);
        } finally {
            session.close();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long createMessage(Message message, Long topicId, Long commentId) throws MessageDaoException {
        Session session = sessionFactory.getCurrentSession();
        try {
            if (topicId != null) {
                message.setTopic((Topic) session.get(Topic.class, topicId));
            }
            if (commentId != null) {
                message.setReplyTo((Message) session.get(Message.class, commentId));
            }
            session.save(message);
            return message.getId();
        } catch (Exception e) {
            logger.error("Error occurred while sabing new comment: " + message.toString(), e);
            throw new MessageDaoException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Message getMessageById(long id) throws MessageDaoException {
        Session session = sessionFactory.openSession();
        try {
            return (Message) session.get(Message.class, id);
        } catch (Exception e) {
            logger.error("Error occurred while getting message: " + id);
            throw new MessageDaoException(e);
        } finally {
            session.close();
        }
    }
}
