package net.ins.xlogger.msg.dao;

import net.ins.xlogger.common.MessageDaoException;
import net.ins.xlogger.msg.entities.Topic;

import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/7/15.
 */
public interface TopicDao {
    Topic getTopicById(long id) throws MessageDaoException;
    List<Topic> listUserTopics(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException;
    long createTopic(Topic topic, Long authorId) throws MessageDaoException;
}
