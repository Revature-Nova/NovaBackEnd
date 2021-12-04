package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller handles all User Information endpoint interactions
 *
 * @date 11/22/2021
 * @author Gregg Friedman, Travis Hood, Erika Johnson, James Brown
 */
@RestController
@RequestMapping(value = "/Nova")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }


    @GetMapping(value = "/ping")
    public  ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }


    @GetMapping(value = "user/userProfile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Integer id){
        return new ResponseEntity<>(userInfoService.getProfile(id), HttpStatus.OK);
    }

    @PostMapping(value = "/user/userProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setProfileInfo(@RequestBody UserProfileDTO userProfileDTO) {
        return new ResponseEntity<>(userInfoService.setProfileInfo(userProfileDTO), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/user/getProfiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllProfiles(){
        return new ResponseEntity<>(userInfoService.getAllProfiles(), HttpStatus.OK);
    }
}