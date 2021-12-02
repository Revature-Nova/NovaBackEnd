package com.revature.nova.clients;

import com.revature.nova.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

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
