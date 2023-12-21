package be.kdg.sailboatproject.exceptions;

public class SailboatException extends RuntimeException {
    public SailboatException() {
        super();
    }

    public SailboatException(String msg) {
        super(msg);
    }

    public SailboatException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SailboatException(Throwable cause) {
        super(cause);
    }

    public SailboatException(String msg, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace) {
        super(msg, cause, enableSuppresion, writeableStackTrace);
    }
}
