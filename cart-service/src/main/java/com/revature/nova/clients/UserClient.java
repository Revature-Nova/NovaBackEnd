package com.revature.nova.clients;

import com.revature.nova.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This client handles requests to the user service
 * Every request requires the session token to be in the headers
 *
 * @date 11/26/2021
 * @author Kollier Martin
 */
@FeignClient(name = "userFeignClient", url = "http://localhost:8082/user-service/Nova")
public interface UserClient {
    @GetMapping("/user/{id}")
    ResponseEntity<User> getUserByID(@PathVariable int id);

    @GetMapping("/user/{username}")
    ResponseEntity<User> getUserByUsername(@PathVariable String username);

    // Method to retrieve User's cart

    // Method to add to User's cart
}
