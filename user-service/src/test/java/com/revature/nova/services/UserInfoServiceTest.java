package com.revature.nova.services;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.clients.CartClient;
import com.revature.nova.helpers.CurrentUser;
import com.revature.nova.models.UserInfoModel;
import com.revature.nova.repositories.UserInfoRepo;
import com.revature.nova.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mockito Test using JUnit 5 for UserInfoService
 *
 * @author James Brown
 * @version 12/2/2021
 */
@ExtendWith({MockitoExtension.class})
public class UserInfoServiceTest {

    @InjectMocks
    UserInfoService userInfoService;

    @Mock
    UserRepo userRepo;

    @Mock
    UserInfoRepo userInfoRepo;

    @Mock
    CartClient cartClient;

    @Mock
    PasswordEncoder passwordEncoder;

    UserRegistrationDTO regDTO = new UserRegistrationDTO("jimbo",
            "bo", "email@mail.com", "jam", "jim");

    //TODO: Figure out how to mock Current User properly
    void testLoadByUsername(){
//        when(userInfoRepo.findByUsername("jimbo")).thenReturn(new UserInfoModel(regDTO));
//
//        assertEquals("jimbo", userInfoService.loadUserByUsername("jimbo").getUsername());
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
        assertEquals("{\"Favorite Genre\":[null],\"Email\":[\"email@mail.com\"],\"Message\":[null],\"Username\":[\"jimbo\"],\"State\":[null]}",
                userInfoService.getAllProfiles());
    }

    //TODO figure out how to mock CurrentUser properly (PowerMock? StaticMock?)
    void testSetProfileInfo(){
//        UserProfileDTO upDTO = new UserProfileDTO("jimbo","email@email.com", "NC",
//                "Simulation", "Hello");
//        UserInfoModel uim = new UserInfoModel(regDTO);
//        uim.setMessage("Hello");
//        uim.setFavoriteGenre("Simulation");
//        uim.setState("NC");
//
//        when(userInfoRepo.findByUsername("jimbo")).thenReturn(new UserInfoModel(regDTO));
//        when(userInfoService.setProfileInfo(upDTO)).thenReturn(uim);
//
//        UserInfoModel uim2 = userInfoService.setProfileInfo(upDTO);
//
//        assertEquals("Hello", uim2.getMessage());
//        assertEquals("Simulation", uim2.getFavoriteGenre());
//        assertEquals("NC", uim2.getState());
    }

    @Test
    void testConstructor(){
        assertEquals(UserInfoService.class, new UserInfoService(userInfoRepo, userRepo, cartClient).getClass());
    }

    @Test
    void testSave(){
        UserInfoModel uif = new UserInfoModel(regDTO);
        lenient().when(userInfoService.save(new UserInfoModel(regDTO))).thenReturn(uif);

        assertEquals("jimbo", uif.getUsername());
        assertEquals("email@mail.com", uif.getEmail());
        assertEquals("bo", uif.getPassword());
    }

}
