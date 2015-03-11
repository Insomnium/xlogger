package net.ins.xlogger.msg.dao;

import net.ins.xlogger.common.MessageDaoException;
import net.ins.xlogger.msg.entities.Message;
import net.ins.xlogger.msg.entities.Topic;

import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/6/15.
 */
public interface MessageDao {
    Message getMessageById(long id) throws MessageDaoException;
    List<Message> listUserMessages(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException;
    List<Message> listTopicMessages(long topicId) throws MessageDaoException;
    long createMessage(Message message) throws MessageDaoException;
}
