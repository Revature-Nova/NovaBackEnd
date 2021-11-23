package io.Nova.springsecurityjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
Abstracts all the Jwt related functions
and create new Jwt and pull up information from a Jwt
 */
@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    /* takes in a token and gives the username*/
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*  returns the token expiration date */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /* takes in a token and figures out what the claims are */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /* Checks to see if the token is expired */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /*takes user details and create a jwt based off those details*/
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        /*returns a jwt for the user*/
        return createToken(claims, userDetails.getUsername());
    }


    /* takes in a claim and uses your username to create a token */
    private String createToken(Map<String, Object> claims, String subject) {
        /*calls the jwt api*/
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                /* expiration time */
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                /*  creates a key with a signature algorithm */
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /* gets the username and checks if the username matches the user details
     and checks of the token is not expired */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
