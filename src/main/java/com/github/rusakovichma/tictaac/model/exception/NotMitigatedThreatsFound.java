package com.github.rusakovichma.tictaac.model.exception;

public class NotMitigatedThreatsFound extends RuntimeException {

    public NotMitigatedThreatsFound() {
    }

    public NotMitigatedThreatsFound(String message) {
        super(message);
    }

    public NotMitigatedThreatsFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMitigatedThreatsFound(Throwable cause) {
        super(cause);
    }

    public NotMitigatedThreatsFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
