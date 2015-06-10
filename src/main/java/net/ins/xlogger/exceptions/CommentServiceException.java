package net.ins.xlogger.exceptions;

/**
 * Created by ins on 3/8/15.
 */
public class CommentServiceException extends ServiceException {
    public CommentServiceException() {
    }

    public CommentServiceException(String message) {
        super(message);
    }

    public CommentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentServiceException(Throwable cause) {
        super(cause);
    }
}
