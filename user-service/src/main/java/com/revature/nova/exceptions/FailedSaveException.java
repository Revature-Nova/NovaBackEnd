package com.revature.nova.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */
public class FailedSaveException extends RuntimeException {
    public FailedSaveException() {
        super();
    }

    public FailedSaveException(String message) {
        super(message);
    }
}