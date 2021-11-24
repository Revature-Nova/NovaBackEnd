package com.revature.nova.controllers;


import com.revature.nova.models.UserModel;
import com.revature.nova.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This controller handles all User endpoint interactions
 *
 * @date 11/22/2021
 * @author Nova
 */
@RestController
public class UserController {
    private final UserModelService userService;

//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    // TODO: WebClient instead?
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    public UserController(UserModelService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> all = userService.allUsers();

        if(all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }


}
