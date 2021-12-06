package com.revature.nova.exceptions;

public class UnauthorizedPrefixException extends Exception {
    public UnauthorizedPrefixException() {
        super();
    }

    public UnauthorizedPrefixException(String message) {
        super(message);
    }
}
