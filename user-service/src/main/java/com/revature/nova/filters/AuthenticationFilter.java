package com.revature.nova.filters;

import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.services.UserInfoService;
import com.revature.nova.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The filter that validates a session token whenever a request is called
 *
 * @date 11/23/2021
 * @author James Brown, Kollier Martin
 */
@Getter @Setter
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final UserInfoService userInfoService;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthenticationFilter(UserInfoService userInfoService, JWTUtil jwtUtil){
        this.userInfoService = userInfoService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationFilter() {
        this.jwtUtil = new JWTUtil();
        this.userInfoService = getUserInfoService();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!request.getRequestURI().equals("/Nova/login") && !request.getRequestURI().equals("/Nova/register")) {
            parseToken(request);
        }

        chain.doFilter(request, response);
    }

    /**
     * This method parses a token
     * 1. Check if given token is valid
     * 2. If no error is thrown, parse token
     * 3. Configure Spring Security to set the authentication
     * 4. After setting the Authentication in the Web context, notify that the user is authenticated by setting the
     *    SecurityContextHolder authentication
     *
     * @param request Request that is sent to the server
     * @throws AuthenticationException based on the system exception that is thrown
     */
    private void parseToken(ServletRequest request) throws AuthenticationException {
        String username;
        String jwt;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestPrefix = httpRequest.getHeader(jwtUtil.getHeader());

        if (requestPrefix != null && requestPrefix.startsWith(jwtUtil.getPrefix())) {
            jwt = requestPrefix.substring(jwtUtil.getPrefix().length());

            try {
                username = jwtUtil.getUsernameFromToken(jwt);
            } catch (IllegalArgumentException e) {
                throw new AuthenticationException("This JWT is not valid.");
            } catch (ExpiredJwtException e) {
                throw new AuthenticationException("JWT Token has expired.");
            }
        } else {
            throw new AuthenticationException("Unauthorized prefix detected! Denied.");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userInfoService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

                SecurityContextHolder.getContext().setAuthentication(upaToken);
            }
        }
    }
}
