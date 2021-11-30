package com.revature.nova.controllers;


import com.revature.nova.clients.ProductClient;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import com.revature.nova.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    private final ProductClient productClient;

    @Autowired
    public UserController(UserModelService userService, ProductClient productClient) {
        this.userService = userService;
        this.productClient = productClient;
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

    @GetMapping(value = "/product/{title}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProduct(@PathVariable String title){
        List<Product> list = productClient.getProduct(title);

        return ResponseEntity.ok()
                .body(list);
    }
}
