package com.revature.nova.clients;

import com.revature.nova.models.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This client handles requests to the product service
 *
 * @date 12/2/2021
 * @author Kollier Martin
 */
@FeignClient(name = "cartFeignClient", url = "http://18.212.102.32:8082/cart-service/Nova")
public interface CartClient {
    @GetMapping("/cart")
    Cart getCart();
}
