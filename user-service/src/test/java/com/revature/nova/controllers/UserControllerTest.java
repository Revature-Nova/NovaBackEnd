package com.revature.nova.controllers;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.services.UserInfoService;
import com.revature.nova.services.UserModelService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Spring Test using MockMvc to generate requests and check status
 * for UserController
 *
 * @author James Brown
 * @version 12/3/21
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @Autowired
    UserModelService userModelService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    MockMvc mockMvc;

    int current_id = 1;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUpEach() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(this.webApplicationContext).build();
        userModelService.clearList();
        userInfoService.registerUser(new UserRegistrationDTO("walter", "white",
                "walter@email.com", "walter", "white"));
    }

    @AfterEach
    void afterEach(){
        userModelService.clearList();
        current_id++;
    }


    @Test
    void getUsers() throws Exception{
        mockMvc.perform(get("http://localhost:8080/Nova/user/all"))
                .andExpect(status().isOk());
    }
    @Test
    void getEmptyUser() throws Exception {
        userModelService.clearList();
        mockMvc.perform(get("http://localhost:8080/Nova/user/all"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getUserByID() throws Exception{
        mockMvc.perform(get("http://localhost:8080/Nova/user/8"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByID() throws Exception{
        mockMvc.perform(delete("http://localhost:8080/Nova/user/8"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteByFirstName() throws Exception{
        mockMvc.perform(delete("http://localhost:8080/Nova/user/walter"))
                .andExpect(status().isOk());
    }
}