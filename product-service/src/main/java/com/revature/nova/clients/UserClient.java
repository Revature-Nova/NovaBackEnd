package com.revature.nova.clients;

import com.revature.nova.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

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
    UserModel getUserByID(@PathVariable int id);

    // Method to retrieve User's cart

    // Method to add to User's cart
}
