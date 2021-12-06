package com.revature.nova.exceptions;

import javax.naming.AuthenticationException;

public class MalformedTokenException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public MalformedTokenException(String msg) {
        super(msg);
    }
}
