package net.ins.xlogger.msg.service.impl;

import net.ins.xlogger.common.CommentServiceException;
import net.ins.xlogger.common.MessageDaoException;
import net.ins.xlogger.msg.MarkupType;
import net.ins.xlogger.msg.MessageRequest;
import net.ins.xlogger.msg.dao.MessageDao;
import net.ins.xlogger.msg.entities.Message;
import net.ins.xlogger.msg.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by ins on 3/8/15.
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private MessageDao messageDao;

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
            return messageDao.createMessage(msg, topicId, message.getCommentId());
        } catch (MessageDaoException e) {
            throw new CommentServiceException(e);
        }
    }
}
