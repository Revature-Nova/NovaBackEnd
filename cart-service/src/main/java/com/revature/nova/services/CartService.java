package com.revature.nova.services;

import com.revature.nova.exceptions.EmptyCartException;
import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import com.revature.nova.models.User;
import com.revature.nova.repositories.CartRepo;
import com.revature.nova.repositories.UserRepo;
import com.revature.nova.utils.JWTUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CartService {
    private final JWTUtil jwtUtil;
    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final LoggerService loggerService;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepo userRepo, LoggerService loggerService) {
        this.loggerService = loggerService;
        this.jwtUtil = new JWTUtil();
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }

    public Cart createCart(String token, String key){
        JSONObject jsonObject = new JSONObject(key);
        Cart cart = new Cart();
        JSONArray bytes = (JSONArray) jsonObject.get("Key");

        jwtUtil.byteToKey(bytes.toString().getBytes(), (String) jsonObject.get("Algorithm"));
        String username = jwtUtil.getUsernameFromToken(token);

        try {
            User user = userRepo.getByUsername(username);
            cart.setUser(user);
        } catch (Exception e) {
            loggerService.writeLog("Tried to get existing user, but this user is new! Creating new user!", 3);

            User newUser = new User(username);
            userRepo.save(newUser);
            cart.setUser(newUser);
        }

        return cartRepo.save(cart);
    }

    public Cart addToCart(Cart cart, Product product){
        cart.getProductList().add(product);

        return cartRepo.save(cart);
    }

    public Cart getCartByID(int id){
        Optional<Cart> cart = cartRepo.findById(id);

        if (cart.isPresent()){
            return cart.get();
        } else {
            throw new EmptyCartException("Cart does not exist!");
        }
    }

    public void deleteCartByID(int id){
        cartRepo.deleteById(id);
    }
}
