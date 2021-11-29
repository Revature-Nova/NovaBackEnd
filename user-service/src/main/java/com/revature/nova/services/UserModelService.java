package com.revature.nova.services;

import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service handles User repo queries
 *
 * @author Gregg Friedman, Travis Hood, Kollier Martin
 * @date 11/23/2021
 */
@Service
@Transactional
public class UserModelService {
    private final UserRepo userRepo;

    @Autowired
    public UserModelService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Returns all users in the postgreSQL database
     *
     * @author Gregg Friedman, Travis Hood, Kollier Martin
     * @date 11/23/2021
     * @return A list of all Users from the database
     */

    public List<UserModel> allUsers(){
        return userRepo.findAll();
    }
}
