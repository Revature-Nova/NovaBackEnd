package com.revature.nova.clients;

import com.revature.nova.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
    public UserClient(){
        client = WebClient.builder()
                .baseUrl("http://localhost:8089")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public UserModel getUserByID(int id){
        return client.get()
                .uri("/Nova/user/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class)
                .block();
    }

    // Method to retrieve User's cart
    // Method to add to User's cart
}
