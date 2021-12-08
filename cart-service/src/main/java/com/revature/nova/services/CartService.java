package com.revature.nova.services;

import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.exceptions.DataException;
import com.revature.nova.exceptions.EmptyCartException;
import com.revature.nova.helpers.CurrentUser;
import com.revature.nova.helpers.Token;
import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import com.revature.nova.models.User;
import com.revature.nova.repositories.CartRepo;
import com.revature.nova.repositories.ProductRepo;
import com.revature.nova.repositories.UserRepo;
import com.revature.nova.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This service handles Cart repo queries
 *
 * @author Kollier Martin
 * @date 12/1/2021
 */
@Service
public class CartService {
    private final JWTUtil jwtUtil;
    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Autowired
    public CartService(JWTUtil jwtUtil, CartRepo cartRepo, UserRepo userRepo, ProductRepo productRepo) {
        this.jwtUtil = jwtUtil;
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Cart createCart(User user){
        Cart cart = new Cart();

        try {
            User returningUser = userRepo.getByUsername(user.getUsername());

            if (returningUser == null) {
                User newUser = userRepo.save(user);
                userRepo.save(newUser);
                cart.setUser(newUser);

                CurrentUser.setUser(newUser);
            } else {
                CurrentUser.setUser(returningUser);
                cart.setUser(returningUser);
            }

        } catch (DataIntegrityViolationException e) {
            throw new DataException(e.getMessage());
        }

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

    public Cart save(String token, Cart cart) {
        Token.setToken(token);
        String prefix = token.substring(0, jwtUtil.getPrefix().length());

        if (prefix.equals(jwtUtil.getPrefix())) {
            for (Product p : cart.getProductList()) {
                if (!productRepo.findById(p.getProductId()).isPresent()){
                    productRepo.save(p);
                }
            }
            cart.setUser(CurrentUser.getUser());
            return cartRepo.save(cart);
        } else {
            throw new AuthenticationException("This token is not valid!");
        }
    }
}
