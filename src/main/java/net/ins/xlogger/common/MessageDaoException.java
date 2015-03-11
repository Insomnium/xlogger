package net.ins.xlogger.common;

/**
 * Created by ins on 3/7/15.
 */
public class MessageDaoException extends DaoException {
    public MessageDaoException() {
    }

    public MessageDaoException(Throwable cause) {
        super(cause);
    }

    public MessageDaoException(String message) {
        super(message);
    }

    public MessageDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
