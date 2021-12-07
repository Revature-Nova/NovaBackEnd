package com.revature.nova.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/22/2021
 * @author Kollier Martin
 */
public class DataException extends RuntimeException {
    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }
}