package com.github.rusakovichma.tictaac.validation;

public class ValidationException extends RuntimeException {

    public ValidationException() {
    }

    public ValidationException(ValidationErrors errors) {
        super(errors.getSummary());
    }

    public ValidationException(ValidationErrors errors, Throwable cause) {
        super(errors.getSummary(), cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(ValidationErrors errors, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errors.getSummary(), cause, enableSuppression, writableStackTrace);
    }
}
