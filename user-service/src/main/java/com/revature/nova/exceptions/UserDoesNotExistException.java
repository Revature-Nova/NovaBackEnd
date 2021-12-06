package com.revature.nova.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}