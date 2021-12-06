package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.services.UserInfoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring Test using MockMvc to generate requests and check status
 * for UserInfoController
 *
 * @author James Brown
 * @version 12/3/21
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserInfoControllerTest {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    void setUpAll(){
        userInfoService.registerUser(new UserRegistrationDTO("walter", "white",
                "walter@email.com", "walter", "white"));
    }

    @BeforeEach
    void setUpEach() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void setProfileInfo() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("walter", "white"));

        mockMvc.perform(post("http://localhost:8089/Nova/user/profile/set")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                        "    \"username\":\"walter\",\n" +
                        "    \"email\": \"hello@email.com\",\n" +
                        "    \"state\": \"NC\",\n" +
                        "    \"favoriteGenre\": \"Horror\",\n" +
                        "    \"message\":\"testing testing 123\"\n" +
                        "}"))
                .andExpect(status().isAccepted());
    }

    @Test
    void getAllProfiles() throws Exception {
        mockMvc.perform(get("http://localhost:8089/Nova/user/profile/all"))
                .andExpect(status().isOk());
    }
}