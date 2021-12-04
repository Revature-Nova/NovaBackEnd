package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.services.UserInfoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring Test using MockMvc to generate requests and check
 * for AuthenticationController
 *
 * @author James Brown
 * @version 12/3/21
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthenticationControllerTest {

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
    void testCreateAuthenticationToken() throws Exception{
        mockMvc.perform(post("http://localhost:8089/Nova/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                        "\"username\":\"walter\"," +
                        " \"password\":\"white\"" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveUser() throws Exception{
        mockMvc.perform(post("http://localhost:8089/Nova/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{" +
                        "    \"firstName\":\"Post\",\n" +
                        "    \"lastName\": \"Man\",\n" +
                        "    \"username\": \"Postman\",\n" +
                        "    \"password\": \"Postman1\",\n" +
                        "    \"email\":\"hello@mail.com\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }
}