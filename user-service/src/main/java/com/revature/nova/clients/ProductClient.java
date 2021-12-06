package com.revature.nova.clients;

import com.revature.nova.helpers.Token;
import com.revature.nova.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.WebClient.create;

/**
 * This client handles requests to the product service
 *
 * @date 11/26/2021
 * @author Kollier Martin
 */
@Service
public class ProductClient {
    private final WebClient client;

    @Autowired
    public ProductClient() {
        client = create("http://localhost:8082/product-service/Nova");
    }

    public Product getProduct(String productTitle) {
        return client
                .get()
                .uri("/title/" + productTitle)
                .header("Authorization", Token.getToken())
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}
