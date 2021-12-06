package com.revature.nova.exceptions;

/**
 * Custom Runtime Exception
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}