package com.revature.nova.controllers;


import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
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

    @GetMapping("/cart")
    public ResponseEntity<Cart> getNewCart(){
        return new ResponseEntity<>(cartService.createCart(), HttpStatus.CREATED);
    }

    @PutMapping("/cart/add")
    public ResponseEntity<Cart> addToCart(Cart cart, @RequestBody Product product){
        return new ResponseEntity<>(cartService.addToCart(cart, product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id){
        cartService.deleteCartByID(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cart/byCart")
    public ResponseEntity<?> deleteCart(@RequestBody Cart cart){
        cartService.deleteCart(cart);

        return ResponseEntity.noContent().build();
    }
}
