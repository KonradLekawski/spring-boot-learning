package com.kodekul.kondrat.bfsword.global.exception;

public class IdNotExistsException extends Exception {

    public IdNotExistsException() {
    }

    public IdNotExistsException(String message) {
        super(message);
    }

    public IdNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotExistsException(Throwable cause) {
        super(cause);
    }

    public IdNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
