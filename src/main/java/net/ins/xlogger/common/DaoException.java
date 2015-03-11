package net.ins.xlogger.common;

/**
 * Created by ins on 3/7/15.
 */
public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
