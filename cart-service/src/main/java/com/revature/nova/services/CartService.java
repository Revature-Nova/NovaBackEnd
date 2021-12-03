package com.revature.nova.services;

import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import com.revature.nova.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {
    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart createCart(){
        return cartRepo.save(new Cart());
    }

    public Cart addToCart(Cart cart, Product product){
        cart.getProductList().add(product);

        return cartRepo.save(cart);
    }

    public void deleteCart(Cart cart){
        cartRepo.delete(cart);
    }

    public void deleteCartByID(int id){
        cartRepo.deleteById(id);
    }
}
