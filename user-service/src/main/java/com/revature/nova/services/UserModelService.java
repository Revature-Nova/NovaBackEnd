package com.revature.nova.services;

import com.revature.nova.clients.CartClient;
import com.revature.nova.exceptions.UserDoesNotExistException;
import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    /**
     * Searches and returns a User based on their ID, if they exist
     *
     * @author Kollier Martin
     * @date 11/29/2021
     * @param id User ID to query for
     * @return User object
     */
    public UserModel findByID(int id) {
       Optional<UserModel> user = userRepo.findById(id);

       if (user.isPresent()){
            return user.get();
       } else {
           throw new UserDoesNotExistException("User with ID " + id + " does not exist.");
       }
    }

    /**
     * Searches and deleted a User based on their ID, if they exist
     *
     * @author Kollier Martin
     * @date 12/1/2021
     * @param id User ID to query for
     */
    public void deleteByID(int id) {
        userRepo.deleteById(id);
    }

    /**
     * Searches and deleted a User based on their ID, if they exist
     *
     * @author Kollier Martin
     * @date 12/1/2021
     * @param firstName First name to query for
     */
    public void deleteByFirstName(String firstName) {
        userRepo.deleteByFirstName(firstName);
    }
}
