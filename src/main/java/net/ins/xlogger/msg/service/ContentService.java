package net.ins.xlogger.msg.service;

import net.ins.xlogger.common.MessageDaoException;
import net.ins.xlogger.msg.domain.PreparedTopic;

import java.util.Date;
import java.util.List;

/**
 * Created by ins on 3/29/15.
 */
public interface ContentService {
    PreparedTopic getTopicById(long id) throws MessageDaoException;
    List<PreparedTopic> listTopics(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException;
}
