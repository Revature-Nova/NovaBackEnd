package com.revature.nova.services;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.repositories.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Erika Johnson
 * Created methods (save, deleteUserInfo, setProfileInfo)
 */
@Service
@Transactional
public class UserInfoService {
    private final UserInfoRepo userInfoRepo;

    @Autowired
    public UserInfoService(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
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
}


