package com.revature.nova.clients;

import com.revature.nova.models.Cart;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This client handles requests to the user service
 * Every request requires the session token to be in the headers
 *
 * @date 11/26/2021
 * @author Kollier Martin
 */
@FeignClient(name = "cartFeignClient", url = "http://localhost:8082/cart-service/Nova")
public interface CartClient {
    // Method to add to cart
    @PostMapping("/cart/add")
    Cart addToCart(Product product);

    // Method to retrieve cart

}
