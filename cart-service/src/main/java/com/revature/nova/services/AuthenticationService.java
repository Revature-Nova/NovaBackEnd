package com.revature.nova.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationService {
    public static String getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (String) auth.getPrincipal();
    }
}
