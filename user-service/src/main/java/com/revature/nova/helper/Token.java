package com.revature.nova.helper;

import lombok.Getter;
import lombok.Setter;

import java.security.Key;

/**
 * Stores the current token and its signing key for use in other services
 *
 * @author Kollier Martin
 * @date 12/3/2021
 */
public class Token {
    @Getter @Setter
    public static String token;

    @Getter @Setter
    public static Key key;
}
