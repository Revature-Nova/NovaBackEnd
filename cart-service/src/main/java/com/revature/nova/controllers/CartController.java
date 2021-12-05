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

    @PostMapping("/cart")
    public ResponseEntity<Cart> getNewCart(@RequestHeader String Authorization, @RequestBody String key){
        return new ResponseEntity<>(cartService.createCart(Authorization, key), HttpStatus.CREATED);
    }

    @PutMapping("/cart/add/{id}")
    public ResponseEntity<Cart> addToCart(@PathVariable int id, @RequestBody Product product){
        Cart cart = cartService.getCartByID(id);
        return new ResponseEntity<>(cartService.addToCart(cart, product), HttpStatus.ACCEPTED);
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
