package io.Nova.springsecurityjwt.controller;

import io.Nova.springsecurityjwt.models.AuthenticationRequest;
import io.Nova.springsecurityjwt.models.AuthenticationResponse;
import io.Nova.springsecurityjwt.services.MyUserDetailsService;
import io.Nova.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    //this is the end point to be reached through authentication
    @RequestMapping({"/"})
    public String rootPage(){
        return "Root Page";
    }

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    /* gets the username and password from the request map */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        /* Authenticate with the username and password given */
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        /* Grabs the user details  */
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        /* I pass in the user details to create a token */
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        /* Responds with 200 ok, and the payload is going to be the Authentication response class */
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

