package com.revature.nova.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */
public class NullCartException extends RuntimeException {
    public NullCartException() {
        super();
    }

    public NullCartException(String message) {
        super(message);
    }
}