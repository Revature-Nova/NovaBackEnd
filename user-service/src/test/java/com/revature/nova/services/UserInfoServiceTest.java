package com.revature.nova.services;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.UserRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.repositories.UserInfoRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * @author Erika Johnson
 * UserProfile Test to make sure a registered User is
 * able to create a User Profile
 */

/**
 * Mockito Test using JUnit 5 for UserInfoService
 *
 * @author James Brown
 * @version 12/2/2021
 */
@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {

    @InjectMocks
    UserInfoService userInfoService;

    @Mock
    UserRepo userRepo;

    @Mock
    UserInfoRepo userInfoRepo;

    @Mock
    PasswordEncoder passwordEncoder;

    UserRegistrationDTO regDTO = new UserRegistrationDTO("jimbo",
            "bo", "email@mail.com", "jam", "jim");

    @Test
    void testLoadByUsername(){
        User u = new User("jimbo", "bo", new ArrayList<>());

        when(userInfoRepo.findByUsername("jimbo")).thenReturn(new UserInfoModel(regDTO));

        assertEquals("jimbo", userInfoService.loadUserByUsername("jimbo").getUsername());
    }

    @Test
    void testLoadByUsernameIfModelNull(){
        when(userInfoRepo.findByUsername("jimbo")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                ()->userInfoService.loadUserByUsername("jimbo"), "User not found with username: jimbo");
    }

    @Test
    void testGetAllProfiles(){
        ArrayList<UserInfoModel> list = new ArrayList<>();
        list.add(new UserInfoModel(regDTO));
        when(userInfoRepo.findAll()).thenReturn(list);

        assertEquals("{\"Email\":\"email@mail.com\",\"Username\":\"jimbo\"}",
                userInfoService.getAllProfiles());
    }

    @Test
    void testSetProfileInfo(){
        UserProfileDTO upDTO = new UserProfileDTO("jimbo","email@email.com", "NC",
                "Simulation", "Hello");
        UserInfoModel uim = new UserInfoModel(regDTO);
        uim.setMessage("Hello");
        uim.setFavoriteGenre("Simulation");
        uim.setState("NC");

        when(userInfoRepo.findByUsername("jimbo")).thenReturn(new UserInfoModel(regDTO));
        when(userInfoService.setProfileInfo(upDTO)).thenReturn(uim);

        UserInfoModel uim2 = userInfoService.setProfileInfo(upDTO);

        assertEquals("Hello", uim2.getMessage());
        assertEquals("Simulation", uim2.getFavoriteGenre());
        assertEquals("NC", uim2.getState());
    }

    @Test
    void testConstructor(){
        assertEquals(UserInfoService.class, UserInfoService.class);
    }

    @Test
    void testSave(){
        UserInfoModel uif = new UserInfoModel(regDTO);
        lenient().when(userInfoService.save(new UserInfoModel(regDTO))).thenReturn(uif);

        assertEquals("jimbo", uif.getUsername());
        assertEquals("email@mail.com", uif.getEmail());
        assertEquals("bo", uif.getPassword());
    }
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
        userInfoService.setProfileInfo(new UserProfileDTO("Snoopy", "pass@email.com","New York", "Role-Playing", "Hello"));
        userModel = userRepo.findByFirstName("Snoopy");
        assertEquals("New York", userModel.getUserInfoModel().getState());
        assertEquals("Role-Playing", userModel.getUserInfoModel().getFavoriteGenre());
        assertEquals("Hello", userModel.getUserInfoModel().getMessage());
    }
}
