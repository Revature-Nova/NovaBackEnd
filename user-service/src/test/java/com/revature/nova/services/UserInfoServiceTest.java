package com.revature.nova.services;
import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Erika Johnson
 * UserProfile Test to make sure a registered User is
 * able to create a User Profile
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserInfoServiceTest {


    private final UserInfoService userInfoService;

    private final UserRepo userRepo;

    @Autowired
    public UserInfoServiceTest(UserInfoService userInfoService, UserRepo userRepo) {
        this.userInfoService = userInfoService;
        this.userRepo = userRepo;
    }

    @BeforeAll
    public void beforeAll() {
        userInfoService.registerUser(new UserRegistrationDTO("Snoopy", "Password1!", "pass@email.com", "Snoopy", "Dog"));
    }

    @Test
    public void UserProfileTest(){
        UserModel userModel;
        userInfoService.setProfileInfo(new UserProfileDTO("Snoopy", "New York", "Role-Playing", "Hello"));
        userModel = userRepo.findByFirstName("Snoopy");
        assertEquals("New York", userModel.getUserInfoModel().getState());
        assertEquals("Role-Playing", userModel.getUserInfoModel().getFavoriteGenre());
        assertEquals("Hello", userModel.getUserInfoModel().getMessage());
    }


}
