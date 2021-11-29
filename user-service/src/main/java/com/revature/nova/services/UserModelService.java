package com.revature.nova.services;

<<<<<<< Updated upstream
import com.revature.nova.clients.ProductClient;
import com.revature.nova.models.Product;
=======
import com.revature.nova.exceptions.UserDoesNotExistException;
>>>>>>> Stashed changes
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

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
     * @date 11/23/2021
     * @return A list of all Users from the database
     */
    public List<UserModel> allUsers(){
        return userRepo.findAll();
    }

<<<<<<< Updated upstream
    public UserModel getUserByID(int id){
        return userRepo.findById(id).get();
=======
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
>>>>>>> Stashed changes
    }
}
