package com.epam.nikitasidorevich.banksystem.dao.connectionpool.exception;

/**
 * @version     1.0 29 Jan 2013
 * @author      Nikita Sidorevich
 */
public class ConnectionPoolException extends Exception {
    private Exception exception;

    public ConnectionPoolException(String er) {
        super(er);
    }

    public ConnectionPoolException(String er, Exception ex) {
        super(er);
        exception = ex;
    }

    public Exception getException() {
        return exception;
    }
}