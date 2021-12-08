package com.revature.nova.utils;

import com.revature.nova.DTOs.UserRegistrationDTO;
import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.services.UserInfoService;
import com.revature.nova.utils.JWTUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Test for the JWTUtil class
 *
 * @author James Brown
 * @version 12/3/2021
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JWTUtilTest {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private String token;
    UserInfoService uis;
    JWTUtil jwtUtil;

    @Autowired
    JWTUtilTest(JWTUtil jwtUtil, UserInfoService userInfoService){
        this.jwtUtil = jwtUtil;
        uis = userInfoService;
    }

//    @BeforeAll
//    void before(){
//        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO("walter", "white",
//                "walter@email.com", "walter", "white");
//        uis.registerUser(userRegistrationDTO);
//        token = jwtUtil.createJWT(uis.loadUserByUsername("walter"));
//    }

    //TODO refactor tests to account for CurrentUser

//    @Test
//    void testParseJWT(){
//        assertEquals(jwtUtil.getUsernameFromToken(token), "walter");
//
//    }
//
//    @Test
//    void testTokenValidation(){
//        String token = jwtUtil.createJWT(uis.loadUserByUsername("walter"));
//        assertTrue(jwtUtil.validateToken(token, uis.loadUserByUsername("walter")));
//    }
//
//    @Test
//    void testBadToken(){
//        assertThrows(AuthenticationException.class, ()->jwtUtil.parseJWT(token+"1"));
//    }


}
