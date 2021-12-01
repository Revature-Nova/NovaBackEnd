package com.revature.nova.services;

import java.util.List;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.models.UserModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for UserModelService
 *
 * @Author Gregg Friedman, James Brown
 * @Version 12/1/2021
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserModelServiceTest {

//    @Autowired
    private final UserModelService userModelService;

//    @Autowired
    private final UserInfoService userInfoService;

    @Autowired
    public UserModelServiceTest(UserModelService userModelService, UserInfoService userInfoService) {
        this.userModelService = userModelService;
        this.userInfoService = userInfoService;
    }

    @BeforeAll
    public void beforeAll(){
        userInfoService.registerUser(new UserRegistrationDTO("Words", "Brown",
                "email@email.com", "Words", "Brown"));
    }

    @Test
    public void testIfUsersArePersisted(){
        List<UserModel> l = userModelService.allUsers();

        assertEquals(1, l.size());
    }

    @Test
    public void testFindByID(){
        UserModel user = userModelService.findByID(1);

        assertEquals("Words",user.getFirstName());
    }


}
