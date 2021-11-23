package io.Nova.springsecurityjwt.models;

import java.io.Serializable;

/*
Output Structure
Responds with a jwt
*/
public class AuthenticationResponse implements Serializable {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
