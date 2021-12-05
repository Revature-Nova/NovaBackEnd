package com.revature.nova.controllers;


import com.revature.nova.clients.CartClient;
import com.revature.nova.helper.Token;
import com.revature.nova.models.UserModel;
import com.revature.nova.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles all User endpoint interactions
 *
 * @date 11/22/2021
 * @author Gregg Friedman, Travis Hood
 */
@RestController
@RequestMapping(value = "/Nova")
public class UserController {
    private final UserModelService userService;
    private final CartClient cartClient;

    @Autowired
    public UserController(UserModelService userService, CartClient cartClient) {
        this.userService = userService;
        this.cartClient = cartClient;
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> all = userService.allUsers();

        if(all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserModel> getUserByID(@PathVariable int id) {
        UserModel user = userService.findByID(id);

        if(user == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable int id) {
        userService.deleteByID(id);
        return ResponseEntity.ok()
                .body("User successfully deleted.");
    }

    @DeleteMapping("/user/{firstName}")
    public ResponseEntity<?> deleteByFirstName(@PathVariable String firstName) {
        userService.deleteByFirstName(firstName);
        return ResponseEntity.ok()
                .body("User successfully deleted.");
    }

    @PostMapping(value = "/user/cart")
    public ResponseEntity<Object> getCart(){
        Object obj = cartClient.getCart();
        System.out.println(obj);

        return ResponseEntity.ok()
                .body(obj);
    }
}
