package com.revature.nova.services;

import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserModelService {
    private final UserRepo userRepo;

    @Autowired
    public UserModelService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * @author Gregg Friedman, Travis Hood, Kollier Martin
     * @date 11/23/2021
     * @return
     *
     * Returns all users in the postgreSQL database
     */

    public List<UserModel> allUsers(){
        return userRepo.findAll();
    }
}
