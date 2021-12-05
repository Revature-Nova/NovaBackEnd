package com.revature.nova.clients;

import com.revature.nova.helpers.Token;
import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Key;

/**
 * This client handles requests to the product service
 *
 * @date 12/2/2021
 * @author Kollier Martin
 */
@Service
public class CartClient {
    private final WebClient client;

    @Autowired
    public CartClient() {
        client = WebClient.create("http://localhost:8082/cart-service/Nova");
    }

    public Cart getCart(String token, Key key) {
        return client
                .post()
                .uri("/cart")
                .body(key, Key.class)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }

    public Cart addToCart(Product product, int id) {
        return client
                .post()
                .uri("/cart/add/" + id)
                .body(product, Product.class)
                .header("Authorization", Token.getToken())
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }
}
