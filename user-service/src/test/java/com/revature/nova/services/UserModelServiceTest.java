//package com.revature.nova.services;
//
//import com.revature.nova.DTOs.UserRegistrationDTO;
//import com.revature.nova.exceptions.UserDoesNotExistException;
//import com.revature.nova.models.UserModel;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
///**
// * Test class for UserModelService
// *
// * @author James Brown, Gregg Friedman
// * @version 12/1/2021
// */
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class UserModelServiceTest {
//
//    private final UserModelService userModelService;
//    private final UserInfoService userInfoService;
//
//    @Autowired
//    UserModelServiceTest(UserModelService userModelService, UserInfoService userInfoService) {
//        this.userModelService = userModelService;
//        this.userInfoService = userInfoService;
//    }
//
//    @BeforeEach
//    public void beforeEach(){
//        userInfoService.registerUser(new UserRegistrationDTO("Words", "Brown",
//                "email@email.com", "Words", "Brown"));
//    }
//
//    @AfterEach
//    public void clear(){userModelService.clearList();}
//
//    @Test
//    public void testAllUsers(){
//        List<UserModel> l = userModelService.allUsers();
//
//        assertEquals(1, l.size());
//     }

//    @Test
//    public void testFindAndDeleteByID(){
//        UserModel user = userModelService.findByID(2);
//
//        assertEquals("Words",user.getFirstName());
//
//        userModelService.deleteByID(2);
//
//        assertThrows(UserDoesNotExistException.class,
//                () -> userModelService.findByID(2),"User with ID 2 does not exist.");
//
//    }
//
//    @Test
//    public void testDeleteByFirstName(){
//        userModelService.deleteByFirstName("Words");
//
//        assertThrows(UserDoesNotExistException.class,
//                () -> userModelService.findByID(3),"User with ID 3 does not exist.");
//    }
//
//}
