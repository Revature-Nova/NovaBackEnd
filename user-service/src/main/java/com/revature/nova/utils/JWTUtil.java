package com.revature.nova.utils;

import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.models.UserInfoModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 * JWTUtil
 *
 * This class will focus on the creation and parsing of Java Web Tokens
 *
 * Subject: The current session username
 * Expiration Time: 24 hours
 *
 * @date 11/22/2021
 * @author User-Feature Team
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

    public JWTUtil() {
        createKey();
    }

    private void createKey() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String createJWT(UserInfoModel userInfoModel) {
        return Jwts.builder()
                .setIssuer("Nova")
                .setSubject(userInfoModel.getUsername())
                .signWith(key)
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
}
