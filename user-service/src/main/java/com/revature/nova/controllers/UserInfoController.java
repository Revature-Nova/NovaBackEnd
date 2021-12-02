package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles all User Information endpoint interactions
 *
 * @date 11/22/2021
 * @author Gregg Friedman, Travis Hood, Erika Johnson
 */
@RestController
@RequestMapping(value = "/Nova")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping(value = "/user/userProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setProfileInfo(@RequestBody UserProfileDTO userProfileDTO) {
        return new ResponseEntity<>(userInfoService.setProfileInfo(userProfileDTO), HttpStatus.ACCEPTED);
    }
}
