package com.revature.nova.controllers;


import com.revature.nova.clients.CartClient;
import com.revature.nova.clients.ProductClient;
import com.revature.nova.helpers.CurrentUser;
import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import com.revature.nova.services.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final ProductClient productClient;

    @Autowired
    public UserController(UserModelService userService, CartClient cartClient, ProductClient productClient) {
        this.userService = userService;
        this.cartClient = cartClient;
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

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable int id) {
        userService.deleteByID(id);
        return ResponseEntity.ok()
                .body("User successfully deleted.");
    }
  
    @DeleteMapping("/user/{firstName}")
    public ResponseEntity<String> deleteByFirstName(@PathVariable String firstName) {
        userService.deleteByFirstName(firstName);
        return ResponseEntity.ok()
                .body("User successfully deleted.");
    }

    @PostMapping(value = "/user/cart")
    public ResponseEntity<Cart> getNewCart(){
        CurrentUser.setCart(cartClient.getNewCart());
        return ResponseEntity.ok()
                .body(CurrentUser.getCart());
    }

    @PutMapping(value = "/user/cart/product/remove")
    public ResponseEntity<Cart> removeProduct(@RequestBody Product product){
        CurrentUser.getCartProducts().remove(product);
        return new ResponseEntity<>(CurrentUser.getCart(), HttpStatus.OK);
    }

    @PutMapping("/user/cart/add/{productTitle}/{platform}")
    public ResponseEntity<Cart> addToCart(@PathVariable String productTitle, @PathVariable String platform){
        CurrentUser.getCartProducts().add(productClient.getProduct(productTitle, platform));
        return new ResponseEntity<>(CurrentUser.getCart(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/cart")
    public ResponseEntity<Cart> getCurrentCart(){
        return ResponseEntity.ok(CurrentUser.getCart());
    }
}
