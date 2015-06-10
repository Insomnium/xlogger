package net.ins.xlogger.msg.service.impl;

import net.ins.xlogger.exceptions.CommentServiceException;
import net.ins.xlogger.exceptions.MessageDaoException;
import net.ins.xlogger.msg.MarkupType;
import net.ins.xlogger.msg.MessageRequest;
import net.ins.xlogger.msg.dao.MessageDao;
import net.ins.xlogger.msg.dao.TopicDao;
import net.ins.xlogger.msg.domain.PreparedTopic;
import net.ins.xlogger.msg.entities.Message;
import net.ins.xlogger.msg.entities.Topic;
import net.ins.xlogger.msg.service.ContentService;
import net.ins.xlogger.util.AuthUtil;
import net.ins.xlogger.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ins on 3/29/15.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private Config config;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PreparedTopic getTopicById(long id) throws MessageDaoException {
        Topic t = topicDao.getTopicById(id);
        return new PreparedTopic(t.getId(), t.getSubject(), t.getBody(), t.getAuthor().getLogin(), t.getPostDate(), null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<PreparedTopic> listTopics(long userId, Date searchDepth, Integer page, Integer limit) throws MessageDaoException {
        List<Topic> topics = topicDao.listUserTopics(AuthUtil.getUserId(), null, page, limit);
        return topics.stream()
                .map(t -> new PreparedTopic(
                        t.getId(),
                        t.getSubject(),
                        t.getBody(),
                        t.getAuthor().getLogin(),
                        t.getPostDate(),
                        config.getMainURI() + "/topic/" + t.getId())).collect(Collectors.toList());
    }


    @Override
    public long addComment(Long topicId, long authorId, MessageRequest message) throws CommentServiceException {
        Message msg = new Message();
        msg.setPostDate(new Date());
        msg.setSubject(message.getSubject());
        msg.setBody(message.getBody());
        if (message.getMarkupType() == null) {
            msg.setMarkupType(MarkupType.PLAIN);
        }

        try {
            return messageDao.createMessage(msg, authorId, topicId, message.getCommentId());
        } catch (MessageDaoException e) {
            throw new CommentServiceException(e);
        }
    }
}
