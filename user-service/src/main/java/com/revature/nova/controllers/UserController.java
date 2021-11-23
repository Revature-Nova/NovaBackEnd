package com.revature.nova.controllers;


import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.UserService;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import com.revature.nova.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserModelService userService;

    @Autowired
    public UserController(UserModelService userService) {
        this.userService = userService;
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> all = userService.allUsers();

        if(all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @CrossOrigin
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        return new ResponseEntity<>(userService.registerUser(userRegistrationDTO), HttpStatus.OK);
    }
}
