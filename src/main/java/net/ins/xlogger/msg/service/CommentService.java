package net.ins.xlogger.msg.service;

import net.ins.xlogger.common.CommentServiceException;
import net.ins.xlogger.msg.MessageRequest;

/**
 * Created by ins on 3/8/15.
 */
public interface CommentService {
    long addComment(Long topicId, long authorId, MessageRequest message) throws CommentServiceException;
}
