package com.revature.nova.services;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.UserService;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserInfoRepo;
import com.revature.nova.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserModelServiceTest {



    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final UserModelService userModelService;
    @Test
    public void testSucessfulRegisteration() {
//        UserModel newUser = new UserModel("Gregg", "Friedman");
        UserRegistrationDTO newRegDTO = new UserRegistrationDTO("Gfr", "password", "email@dot.com", "first", "last");
//        UserInfoModel newUserInfo = new UserInfoModel("Gfr", "123", "gregg@friedman.com");


        assertEquals(HttpStatus.CREATED, userModelService.registerUser(newRegDTO));
//        assertEquals(HttpStatus.CREATED, userInfoRepo.save(newUserInfo));
    }
}
