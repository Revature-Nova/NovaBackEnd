package com.revature.nova.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This utility focuses on the creation and parsing of Java Web Tokens using designated secret credentials
 *
 * Subject: The current session username
 * Expiration Time: 24 hours
 *
 * @date 11/22/2021
 * @author Kollier Martin, James Brown, Emmanuel Tejeda
 */

@Component
@Getter @Setter
public class JWTUtil {
    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("#{24*60*60*1000}")
    private int expiration;

    private Key key;

    @Autowired
    public JWTUtil() {
    }

    /**
     * Generates a new JWT based on username
     *
     * @param userDetails identifies user by username
     * @return JWT that is valid for 1 day
     */
    public String createJWT(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setIssuer("Nova")
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Claims parseJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
