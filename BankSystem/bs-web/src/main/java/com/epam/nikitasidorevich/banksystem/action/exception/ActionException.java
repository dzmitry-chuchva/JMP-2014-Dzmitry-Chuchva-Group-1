package com.epam.nikitasidorevich.banksystem.action.exception;

public class ActionException extends Exception {
    private Exception exception;

    public ActionException(String errorMessage) {
        super(errorMessage);
    }

    public ActionException(String errorMessage, Exception exception) {
        super(errorMessage);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
