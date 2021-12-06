package com.revature.nova.exceptions;

import javax.naming.AuthenticationException;

public class MissingTokenException  extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public MissingTokenException(String msg) {
        super(msg);
    }
}
