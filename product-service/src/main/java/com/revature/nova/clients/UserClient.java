package com.revature.nova.clients;

import com.revature.nova.helpers.Token;
import com.revature.nova.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.WebClient.create;

/**
 * This client handles requests to the user service
 * Every request requires the session token to be in the headers
 *
 * @date 11/26/2021
 * @author Kollier Martin
 */
@Service
public class UserClient {
    private final WebClient client;

    @Autowired
    public UserClient() {
        client = create("http://localhost:8082/user-service/Nova");
    }

    public UserModel getUserByID(int id) {
        return client
                .get()
                .uri("/cart")
                .header("Authorization", Token.getToken())
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    public UserModel getUserByUsername(String username) {
        return client
                .get()
                .uri("/user/" + username)
                .header("Authorization", Token.getToken())
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }
}
