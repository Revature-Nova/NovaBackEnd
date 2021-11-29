package com.revature.nova.clients;

import com.revature.nova.models.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class ProductClient {
    private final WebClient client;

    public ProductClient(){
        client = WebClient.builder()
                .baseUrl("http://localhost:8090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public List<Product> getProduct(String productTitle){
        return client.get()
                .uri("/Nova/title/" + productTitle)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(Product.class)
                .buffer()
                .collectList()
                .block()
                .get(0);
    }
}
