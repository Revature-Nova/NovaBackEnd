package com.revature.nova.clients;

import com.revature.nova.helper.Token;
import com.revature.nova.models.Cart;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public Cart getCart() {
        return client
                .post()
                .uri("/cart")
                .header("Authorization", Token.getToken())
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }
}
