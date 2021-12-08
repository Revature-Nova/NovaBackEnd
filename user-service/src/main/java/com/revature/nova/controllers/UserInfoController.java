package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.models.UserModel;
import com.revature.nova.services.UserInfoService;
import com.revature.nova.services.UserModelService;
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
    private final UserModelService userModelService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, UserModelService userModelService) {
        this.userInfoService = userInfoService;
        this.userModelService = userModelService;
    }


    @GetMapping(value = "/ping")
    public  ResponseEntity<String> ping() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }


    @PostMapping(value = "user/profile")
    public ResponseEntity<?> getUserProfile( @RequestBody UserProfileDTO userProfileDTO){
        
        return new ResponseEntity<>(userInfoService.setProfileInfoWithOutAuth(userProfileDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/user/profile/set", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setProfileInfo(@RequestBody UserProfileDTO userProfileDTO) {
        return new ResponseEntity<>(userInfoService.setProfileInfo(userProfileDTO), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/user/profile/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllProfiles(){
        return new ResponseEntity<>(userInfoService.getAllProfiles(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProfile(){
        return new ResponseEntity<>(userInfoService.getCurrentProfile(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/profile/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProfile(@PathVariable String username){
        return new ResponseEntity<>(userInfoService.getProfileByUsername(username), HttpStatus.OK);
    }
}