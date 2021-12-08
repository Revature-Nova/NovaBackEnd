package com.revature.nova.controllers;


import com.revature.nova.models.Cart;
import com.revature.nova.models.User;
import com.revature.nova.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller handles all User endpoint interactions
 *
 * @date 11/22/2021
 * @author Gregg Friedman, Travis Hood
 */
@RestController
@RequestMapping(value = "/Nova")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> getNewCart(@RequestBody User user){
        return new ResponseEntity<>(cartService.createCart(user), HttpStatus.CREATED);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCartByID(@PathVariable int id){
        return new ResponseEntity<>(cartService.getCartByID(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id){
        cartService.deleteCartByID(id);

        return ResponseEntity.ok("Deleted");
    }
}
