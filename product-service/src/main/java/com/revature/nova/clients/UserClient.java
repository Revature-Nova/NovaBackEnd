package com.revature.nova.clients;


import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.client.WebClient.create;

public class UserClient {
    private final WebClient client;

    public UserClient(){
        client = create("localhost:8089");
    }

    public Mono<UserModel> getUserByID(int id){
        return client.get()
                .uri("/Nova/user/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(UserModel.class);
    }
}
