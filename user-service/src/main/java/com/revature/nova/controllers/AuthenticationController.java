package com.revature.nova.controllers;

import com.revature.nova.DTOs.LoginCredentialsDTO;
import com.revature.nova.DTOs.RegisteredDataDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.helper.Token;
import com.revature.nova.services.UserInfoService;
import com.revature.nova.utils.JWTUtil;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Handles authentication for user login and registration
 *
 * @date 11/23/2021
 * @author Kollier Martin, James Brown, Emmanuel Tejeda
 */
@RestController
@RequestMapping(value = "/Nova")
public class AuthenticationController {
    private final UserInfoService userInfoService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthenticationController(JWTUtil jwtUtil, AuthenticationManager authenticationManager, UserInfoService userInfoService){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody LoginCredentialsDTO loginDTO) throws Exception {
        String token = jwtUtil.getPrefix() + "";
        JSONObject jsonObj = new JSONObject();

        if (authenticate(loginDTO.getUsername(), loginDTO.getPassword())) {
            UserDetails userDetails = userInfoService.loadUserByUsername(loginDTO.getUsername());
            token += jwtUtil.createJWT(userDetails);

            Token.setToken(token);
            jsonObj.put("token", token);

            return new ResponseEntity<>(jsonObj.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User does not exist.", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredDataDTO> saveUser(@RequestBody @Valid UserRegistrationDTO user) {
        return new ResponseEntity<>(userInfoService.registerUser(user), HttpStatus.CREATED);
    }

    public boolean authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("These credentials are wrong! Have you tried caps lock?");
        }
    }
}
