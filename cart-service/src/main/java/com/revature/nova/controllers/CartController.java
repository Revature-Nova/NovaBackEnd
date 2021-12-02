package com.revature.nova.controllers;


import com.revature.nova.clients.ProductClient;
import com.revature.nova.clients.UserClient;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
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
public class CartController {
    private final UserClient userClient;
    private final ProductClient productClient;

    @Autowired
    public CartController(UserClient userClient, ProductClient productClient) {
        this.userClient = userClient;
        this.productClient = productClient;
    }

    @GetMapping(value = "/user/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getUserByID(@PathVariable int id) {
        UserModel user = userClient.getUserByID(id);

        if(user == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/product/{title}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProduct(@PathVariable String title){
        List<Product> list = productClient.getProduct(title);

        return ResponseEntity.ok(list);
    }
}
