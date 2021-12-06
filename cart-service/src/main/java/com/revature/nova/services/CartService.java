package com.revature.nova.services;

import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.exceptions.EmptyCartException;
import com.revature.nova.helpers.CurrentUser;
import com.revature.nova.helpers.Token;
import com.revature.nova.models.Cart;
import com.revature.nova.models.User;
import com.revature.nova.repositories.CartRepo;
import com.revature.nova.repositories.UserRepo;
import com.revature.nova.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final JWTUtil jwtUtil;
    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final LoggerService loggerService;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepo userRepo, JWTUtil jwtUtil, LoggerService loggerService) {
        this.loggerService = loggerService;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
    }

    public Cart createCart(String token, User user){
        Token.setToken(token);
        String prefix = token.substring(0, jwtUtil.getPrefix().length());
        if (prefix.equals(jwtUtil.getPrefix())) {
            Cart cart = new Cart();

            try {
                User returningUser = userRepo.getByUsername(user.getUsername());

                if (returningUser == null) {
                    User newUser = userRepo.save(user);
                    userRepo.save(newUser);
                    cart.setUser(newUser);

                    CurrentUser.setUser(newUser);
                } else {
                    cart.setUser(returningUser);
                }

                CurrentUser.setUser(returningUser);
            } catch (DataIntegrityViolationException e) {
                loggerService.writeLog(e.getMessage(), 1);
            }

            return cartRepo.save(cart);
        } else {
            throw new AuthenticationException("This token is not valid!");
        }
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
