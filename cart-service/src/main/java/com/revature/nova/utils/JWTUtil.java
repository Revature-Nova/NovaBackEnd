package com.revature.nova.utils;

import com.revature.nova.exceptions.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    private void createKey() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

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

    public Claims parseJWT(String token) throws AuthenticationException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token) {
        return parseJWT(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return parseJWT(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
