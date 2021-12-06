package com.revature.nova.exceptions;

public class NullProductException extends RuntimeException{
    public NullProductException() {
    }

    public NullProductException(String message) {
        super(message);
    }
}
