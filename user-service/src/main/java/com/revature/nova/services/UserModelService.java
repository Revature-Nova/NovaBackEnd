package com.revature.nova.services;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserInfoRepo;
import com.revature.nova.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserModelService {
    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;

    @Autowired
    public UserModelService(UserRepo userRepo, UserInfoRepo userInfoRepo) {
        this.userRepo = userRepo;
        this.userInfoRepo = userInfoRepo;
    }

    /**
     *
     * @author Gregg Friedman, Travis Hood
     * #date 11/23/2021
     * @return
     *
     * Receives information to create a new user, saves it to postgreSQL
     * and returns the information in a string format
     */
    public HttpStatus registerUser(UserRegistrationDTO userRegDTO) {
        UserModel newUser = new UserModel(userRegDTO);
        UserInfoModel newUserInfo = new UserInfoModel(userRegDTO);

        // TODO: Test and see the save order for the models
        // Parent first, then child
        newUser.setUserInfoModel(newUserInfo);
        newUserInfo.setUserModel(newUser);

        newUser = userRepo.save(newUser);
        newUserInfo = userInfoRepo.save(newUserInfo);

        return HttpStatus.CREATED;
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
