package com.revature.nova.services;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.repositories.UserInfoRepo;
import com.revature.nova.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@AllArgsConstructor(onConstructor = @__(@Autowired))
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserModelServiceTest {



    private final UserRepo userRepo;
    private final UserInfoRepo userInfoRepo;
    private final UserInfoService userInfoService;
}
