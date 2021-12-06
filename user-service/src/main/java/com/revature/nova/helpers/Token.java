package com.revature.nova.helpers;

import lombok.Getter;
import lombok.Setter;

/**
 * Stores the current token for use in requests to other services
 *
 * @date 12/3/2021
 * @author Kollier Martin
 */
public class Token {
    @Getter @Setter
    public static String token;
}
