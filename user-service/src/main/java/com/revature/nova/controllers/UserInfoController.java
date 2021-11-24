package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/Nova")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Autowired
//    private RestTemplate restTemplate;

    @PostMapping(value = "/userProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userProfileInfo(@RequestBody UserProfileDTO userProfileDTO) {
        return new ResponseEntity<>(userInfoService.setProfileInfo(userProfileDTO), HttpStatus.ACCEPTED);
    }
}
