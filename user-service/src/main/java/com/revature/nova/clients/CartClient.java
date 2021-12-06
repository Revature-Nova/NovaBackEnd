package com.revature.nova.clients;

import com.revature.nova.exceptions.AuthenticationException;
import com.revature.nova.helpers.CurrentUser;
import com.revature.nova.helpers.Token;
import com.revature.nova.models.Cart;
import com.revature.nova.models.UserInfoModel;
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

    public Cart getNewCart() throws AuthenticationException {
        String token;
        UserInfoModel user;
        try {
            user = CurrentUser.getUser();
            token = Token.getToken();
        } catch (NullPointerException e) {
            throw new AuthenticationException("There is no user currently logged in!");
        }

        return client
                .post()
                .uri("/cart")
                .header("Authorization", token)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(Cart.class)
                .block();
    }
}
