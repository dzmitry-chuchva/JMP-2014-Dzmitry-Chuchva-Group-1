package com.epam.nikitasidorevich.banksystem.exception;

public class DAOException extends Exception {
    private Exception exception;

    public DAOException(String er) {
        super(er);
    }

    public DAOException(String er, Exception ex) {
        super(er);
        exception = ex;
    }

    public Exception getException() {
        return exception;
    }
}