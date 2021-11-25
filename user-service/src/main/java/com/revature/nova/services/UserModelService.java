package com.revature.nova.services;

import com.revature.nova.clients.ProductClient;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

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
     * @date 11/23/2021
     * @return A list of all Users from the database
     */
    public List<UserModel> allUsers(){
        return userRepo.findAll();
    }

    public UserModel getUserByID(int id){
        return userRepo.findById(id).get();
    }
}
