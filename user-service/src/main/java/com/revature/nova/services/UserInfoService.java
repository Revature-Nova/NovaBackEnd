package com.revature.nova.services;

import com.revature.nova.DTOs.RegisteredDataDTO;
import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.helpers.CurrentUser;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserInfoRepo;
import com.revature.nova.repositories.UserRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service used that communicates and queries the database for the storing and retrieving User Information
 *
 * @date 11/23/2021
 * @author Erika Johnson, Gregg Friedman, Travis Hood, Kollier Martin
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

    /**
     * Overridden Spring security method for loading Users
     *
     * @author Kollier Martin, James Brown
     * @date 12/2/21
     * @param username username provided by client for login
     * @return UserDetails used to generate JWT
     * @throws UsernameNotFoundException if findByUsername returns null
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoModel userModel = userInfoRepo.findByUsername(username);

        if (userModel != null) {
            CurrentUser.setUser(userModel);
            return new User(userModel.getUsername(), userModel.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserInfoModel findByUsername(String username){
        return userInfoRepo.findByUsername(username);
    }

    /**
     * Saves a user's information
     *
     * @author Erika Johnson
     * @param userInfo The data to persist
     * @return The newly persisted data
     */
    public UserInfoModel save (UserInfoModel userInfo){
        return userInfoRepo.save(userInfo);
    }

    /**
     * This method sets the profile data of a user
     *
     * @author Erika Johnson, Kollier Martin
     * @param userProfileDTO The DTO that stores profile data
     * @return User Info Model with updated user profile information
     */
    public UserInfoModel setProfileInfo(UserProfileDTO userProfileDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserInfoModel userInfoModel = userInfoRepo.findByUsername((String) auth.getPrincipal());

        userInfoModel.setEmail(userProfileDTO.getEmail());
        userInfoModel.setMessage(userProfileDTO.getMessage());
        userInfoModel.setState(userProfileDTO.getState());
        userInfoModel.setFavoriteGenre(userProfileDTO.getFavoriteGenre());

        return userInfoRepo.save(userInfoModel);
    }


    /**
     * Receives information to create a new user, saves it to postgreSQL
     * and returns the information in a string format
     *
     * During the registration process, the user password is encoded before persistence
     *
     * @author Gregg Friedman, Travis Hood, Kollier Martin
     * @return JSONString with User information in it
     */
    public RegisteredDataDTO registerUser(UserRegistrationDTO userRegDTO) {
        UserModel newUser = new UserModel(userRegDTO);
        UserInfoModel newUserInfo = new UserInfoModel(userRegDTO);

        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));

        // Save User Info, then set it in User
        newUserInfo = userInfoRepo.save(newUserInfo);
        newUser.setUserInfoModel(newUserInfo);

        // Save User after persisting the User Info
        newUser = userRepo.save(newUser);
        newUserInfo.setUserModel(newUser);

        return new RegisteredDataDTO(newUser, newUserInfo);
    }

    /**
     * Retrieves all available user info for user profiles
     *
     * @author Kollier Martin, James Brown
     * @return String of generated JSON Object
     */
    public String getAllProfiles(){
        JSONObject jsonObject = new JSONObject();
        List<UserInfoModel> profileDatum = userInfoRepo.findAll();
        String[] dataName = new String[]{"Username", "Email", "State", "Favorite Genre", "Message"};

        for (UserInfoModel profileData : profileDatum) {
            jsonObject.append(dataName[0], profileData.getUsername());
            jsonObject.append(dataName[1], profileData.getEmail());
            jsonObject.append(dataName[2], profileData.getState());
            jsonObject.append(dataName[3], profileData.getFavoriteGenre());
            jsonObject.append(dataName[4], profileData.getMessage());
        }

        return jsonObject.toString();
    }

    /**
     * Retrieves current user info for user profiles
     *
     * @author Kollier Martin
     * @return String of generated JSON Object
     */
    public String getCurrentProfile(){
        JSONObject jsonObject = new JSONObject();
        List<UserInfoModel> profileDatum = userInfoRepo.findAll();
        String[] dataName = new String[]{"Username", "Email", "State", "Favorite Genre", "Message"};

        for (UserInfoModel profileData : profileDatum) {
            if (profileData.getUsername().equals(CurrentUser.getUser().getUsername())) {
                jsonObject.append(dataName[0], profileData.getUsername());
                jsonObject.append(dataName[1], profileData.getEmail());
                jsonObject.append(dataName[2], profileData.getState());
                jsonObject.append(dataName[3], profileData.getFavoriteGenre());
                jsonObject.append(dataName[4], profileData.getMessage());
            }
        }

        return jsonObject.toString();
    }
}


