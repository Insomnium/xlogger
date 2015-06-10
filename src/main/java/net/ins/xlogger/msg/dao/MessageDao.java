package net.ins.xlogger.msg.dao;

import net.ins.xlogger.exceptions.MessageDaoException;
import net.ins.xlogger.msg.entities.Message;

import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/6/15.
 */
public interface MessageDao {
    Message getMessageById(long id) throws MessageDaoException;
    List<Message> listUserMessages(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException;
    List<Message> listTopicMessages(long topicId) throws MessageDaoException;
    long createMessage(Message message, Long authorId, Long topicId, Long commentId) throws MessageDaoException;
}
