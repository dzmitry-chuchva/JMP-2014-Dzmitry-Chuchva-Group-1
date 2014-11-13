package com.epam.nikitasidorevich.banksystem.service.exception;

public class ServiceException extends Exception {
    private Exception exception;

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceException(String errorMessage, Exception exception) {
        super(errorMessage);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
