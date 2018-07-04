package top.androidman.smartlayout;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class SmartException extends RuntimeException {

    public SmartException() {
    }

    public SmartException(String message) {
        super(message);
    }

    public SmartException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartException(Throwable cause) {
        super(cause);
    }

    public SmartException(String message, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
