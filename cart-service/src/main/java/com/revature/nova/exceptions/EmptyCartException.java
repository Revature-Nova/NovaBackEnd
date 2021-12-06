package com.revature.nova.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */
public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super();
    }

    public EmptyCartException(String message) {
        super(message);
    }
}