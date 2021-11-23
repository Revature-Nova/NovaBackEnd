package com.revature.nova.services;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterUserService {
    private final UserRepo userRepo;

    @Autowired
    public RegisterUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public HttpStatus registerUser(UserRegistrationDTO userRegDTO){
        //if username already exists, return a 403
        //?
    }

}
