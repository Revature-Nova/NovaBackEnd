package com.revature.nova.clients;

import com.revature.nova.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * This client handles requests to the product service
 *
 * @date 11/26/2021
 * @author Kollier Martin
 */
@FeignClient(name = "productFeignClient", url = "http://localhost:8082/product-service/Nova")
public interface ProductClient {
    @GetMapping("/title/{productTitle}")
    List<Product> getProduct(@PathVariable String productTitle);
}
