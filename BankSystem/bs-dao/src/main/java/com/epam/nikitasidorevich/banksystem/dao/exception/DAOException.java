package com.epam.nikitasidorevich.banksystem.dao.exception;

public class DAOException extends Exception {
    private Exception exception;

    public DAOException(String errorMessage) {
        super(errorMessage);
    }

    public DAOException(String errorMessage, Exception exception) {
        super(errorMessage);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}