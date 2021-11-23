package io.Nova.springsecurityjwt.filters;

import io.Nova.springsecurityjwt.services.MyUserDetailsService;
import io.Nova.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Intercept every request once and examine the header
*/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /* Examine the incoming request for the jwt in the header and see if that jwt is valid */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        /*  if the jwt is valid this will contain Bearer "jwt" */
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        /*if the authorization is not null and contain "Bearer " */
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            /* Gets the raw jwt without the bearer */
            jwt = authorizationHeader.substring(7);
            /* Gets the username of the user*/
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            /*  Gets the user details */
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            /*  Validates the token with the user details */
            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        /* continues whatever filters are needed to be processed  */
        chain.doFilter(request, response);
    }
}
