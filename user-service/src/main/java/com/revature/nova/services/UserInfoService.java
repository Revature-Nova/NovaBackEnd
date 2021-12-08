package com.revature.nova.services;

import com.revature.nova.DTOs.RegisteredDataDTO;
import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.exceptions.UserDoesNotExistException;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Service used that communicates and queries the database for the storing and retrieving User Information
 *
 * @version 12/3/2021
 * @author User-Service Team
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
        try {
            UserInfoModel userModel = userInfoRepo.findByUsername(username);

            CurrentUser.setUser(userModel);
            CurrentUser.setUsername(userModel.getUsername());

            return new User(userModel.getUsername(), userModel.getPassword(),
                    new ArrayList<>());
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    /**
     * Return a user by username
     *
     * @author Kollier Martin
     * @param username username to query with
     * @return user from database
     */
    public UserInfoModel findByUsername(String username){
        return userInfoRepo.findByUsername(username);
    }


    public UserInfoModel findUserById(int id ){ return userInfoRepo.getById(id);}
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
        UserInfoModel userInfoModel = userInfoRepo.findByUsername(CurrentUser.getUsername());

        userInfoModel.setEmail(userProfileDTO.getEmail());
        userInfoModel.setMessage(userProfileDTO.getMessage());
        userInfoModel.setState(userProfileDTO.getState());
        userInfoModel.setFavoriteGenre(userProfileDTO.getFavoriteGenre());

        return userInfoRepo.save(userInfoModel);
    }
    public UserInfoModel setProfileInfoWithOutAuth(UserProfileDTO userProfileDTO) {

        UserInfoModel userInfoModel = userInfoRepo.findByUsername(userProfileDTO.getUsername());

        userInfoModel.setEmail(userProfileDTO.getEmail());
        userInfoModel.setMessage(userProfileDTO.getMessage());
        userInfoModel.setState(userProfileDTO.getState());
        userInfoModel.setFavoriteGenre(userProfileDTO.getFavoriteGenre());
        userInfoRepo.save(userInfoModel);

        UserInfoModel responseModel = new UserInfoModel();
        responseModel.setUsername(userInfoModel.getUsername());
        responseModel.setEmail(userProfileDTO.getEmail());
        responseModel.setMessage(userProfileDTO.getMessage());
        responseModel.setState(userProfileDTO.getState());
        responseModel.setFavoriteGenre(userProfileDTO.getFavoriteGenre());

        return responseModel;
    }

    public UserInfoModel getProfile(Integer id){
        return userInfoRepo.getById(id);
    }

    /**
     * Receives information to create a new user, saves it to Postgres
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

        newUserInfo = userInfoRepo.save(newUserInfo);
        newUser.setUserInfoModel(newUserInfo);

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
    public String getAllProfiles() throws UserDoesNotExistException {
        JSONObject jsonObject = new JSONObject();
        List<UserInfoModel> profileDatum = userInfoRepo.findAll();

        if (!profileDatum.isEmpty()) {
            String[] dataName = new String[]{"Username", "Email", "State", "FavoriteGenre", "Message"};

            for (UserInfoModel profileData : profileDatum) {
                jsonObject.append(dataName[0], profileData.getUsername());
                jsonObject.append(dataName[1], profileData.getEmail());
                jsonObject.append(dataName[2], profileData.getState());
                jsonObject.append(dataName[3], profileData.getFavoriteGenre());
                jsonObject.append(dataName[4], profileData.getMessage());
            }
        } else {
            throw new UserDoesNotExistException("There are no users currently in this repository!");
        }

        return jsonObject.toString();
    }

    /**
     * Retrieves current user info for user profiles
     *
     * @author Kollier Martin
     * @return String of generated JSON Object
     */
    public String getCurrentProfile() throws UserDoesNotExistException {
        JSONObject jsonObject = new JSONObject();
        List<UserInfoModel> profileDatum = userInfoRepo.findAll();

        if (!profileDatum.isEmpty()) {
            String[] dataName = new String[]{"Username", "Email", "State", "FavoriteGenre", "Message"};

            for (UserInfoModel profileData : profileDatum) {
                if (profileData.getUsername().equals(CurrentUser.getUser().getUsername())) {
                    jsonObject.append(dataName[0], profileData.getUsername());
                    jsonObject.append(dataName[1], profileData.getEmail());
                    jsonObject.append(dataName[2], profileData.getState());
                    jsonObject.append(dataName[3], profileData.getFavoriteGenre());
                    jsonObject.append(dataName[4], profileData.getMessage());
                }
            }
        } else {
            throw new UserDoesNotExistException("There are no users currently in this repository!");
        }

        return jsonObject.toString();
    }

    /**
     * Retrieves profile information for a specific user
     *
     * @author Kollier Martin
     * @return String of generated JSON Object
     */
    public String getProfileByUsername(String username) throws UserDoesNotExistException {
        JSONObject jsonObject = new JSONObject();
        List<UserInfoModel> profileDatum = userInfoRepo.findAll();

        if (!profileDatum.isEmpty()) {
            String[] dataName = new String[]{"Username", "Email", "State", "FavoriteGenre", "Message"};

            for (UserInfoModel profileData : profileDatum) {
                if (profileData.getUsername().equals(username)) {
                    jsonObject.append(dataName[0], profileData.getUsername());
                    jsonObject.append(dataName[1], profileData.getEmail());
                    jsonObject.append(dataName[2], profileData.getState());
                    jsonObject.append(dataName[3], profileData.getFavoriteGenre());
                    jsonObject.append(dataName[4], profileData.getMessage());
                }
            }
        } else {
            throw new UserDoesNotExistException("There are no users currently in this repository!");
        }

        return jsonObject.toString();
    }
}


