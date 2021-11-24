package com.revature.nova.services;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserInfoRepo;
import com.revature.nova.repositories.UserRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author Erika Johnson
 * Created methods (save, deleteUserInfo, setProfileInfo)
 */
@Service
@Transactional
public class UserInfoService implements UserDetailsService {
    private final UserInfoRepo userInfoRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UserInfoRepo userInfoRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.userInfoRepo = userInfoRepo;
        this.encoder = new BCryptPasswordEncoder();
    }

    public UserInfoModel save (UserInfoModel userInfo){
        return userInfoRepo.save(userInfo);
    }

    public void deleteUserInfo(UserInfoModel userInfo){
        userInfoRepo.delete(userInfo);
    }

    //TODO: Finish method not completed
    public UserProfileDTO setProfileInfo(UserProfileDTO userProfileDTO) {
        UserProfileDTO userProfile = userProfileDTO;
        return userProfileDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoModel userModel = userInfoRepo.findByUsername(username);

        if (userModel != null) {
            return new User(userModel.getUsername(), userModel.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    /**
     *
     * @author Gregg Friedman, Travis Hood
     * #date 11/23/2021
     * @return
     *
     * Receives information to create a new user, saves it to postgreSQL
     * and returns the information in a string format
     *
     * During the registration process, the user password is encoded before persistence
     */
    public String registerUser(UserRegistrationDTO userRegDTO) {
        UserModel newUser = new UserModel(userRegDTO);
        UserInfoModel newUserInfo = new UserInfoModel(userRegDTO);

        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));

        // Save User Info, then set it in User
        newUserInfo = userInfoRepo.save(newUserInfo);
        newUser.setUserInfoModel(newUserInfo);

        // Save User after persisting the User Info
        newUser = userRepo.save(newUser);
        newUserInfo.setUserModel(newUser);

        return newUser.toString();
    }
}


