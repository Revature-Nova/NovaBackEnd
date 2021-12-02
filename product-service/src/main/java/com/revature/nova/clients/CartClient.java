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
public class CartClient {
    private final WebClient client;

    @Autowired
    public CartClient(){
        client = WebClient.builder()
                .baseUrl("http://localhost:8091")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    // Method to retrieve cart
    // Method to add to cart
}
