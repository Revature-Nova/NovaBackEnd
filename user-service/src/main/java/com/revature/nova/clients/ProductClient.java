package com.revature.nova.clients;

import com.revature.nova.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * This client handles requests to the product service
 *
 * @date 11/26/2021
 * @author Kollier Martin
 */
@FeignClient(name = "productFeignClient", url = "${product-service.url}")
public interface ProductClient {
    private final WebClient client;

    @Autowired
    public ProductClient(){
        client = WebClient.builder()
                .baseUrl("http://localhost:8090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @GetMapping("/title/")

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
