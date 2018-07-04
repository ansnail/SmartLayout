package top.androidman.autolayout;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class AutoException extends RuntimeException {

    public AutoException() {
    }

    public AutoException(String message) {
        super(message);
    }

    public AutoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutoException(Throwable cause) {
        super(cause);
    }

    public AutoException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
