package com.revature.nova.controllers;


import com.revature.nova.clients.ProductClient;
import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import com.revature.nova.services.UserModelService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> Stashed changes
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;
import java.util.List;

import static org.springframework.http.MediaType.*;

/**
 * This controller handles all User endpoint interactions
 *
 * @date 11/22/2021
 * @author Gregg Friedman, Travis Hood
 */
@RestController
@RequestMapping(value = "/Nova")
public class UserController {
    private final UserModelService userService;
    private final ProductClient productClient;

    @Autowired
    public UserController(UserModelService userService) {
        this.userService = userService;
        this.productClient = new ProductClient();
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserModel>> getUsers() {
        List<UserModel> all = userService.allUsers();

        if(all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("/user/{id}")
<<<<<<< Updated upstream
    public ResponseEntity<UserModel> getUserByID(@PathVariable int id) {
        UserModel user = userService.getUserByID(id);

        if(user == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
    }

    // TODO: NOT RETURNING THE DATA!
    @GetMapping(value = "/product", produces = ALL_VALUE)
    public ResponseEntity<String> getProduct(){
        JSONObject jsonObject = new JSONObject();
        List<Product> list = productClient.getProduct("Terraria");

        for (Product p : list) {
            jsonObject.put(String.format("Product %d", p.getProductId()), p);
        }

        return ResponseEntity.ok()
                .body(jsonObject.toString());
=======
    public ResponseEntity<UserModel> getUserByID(@PathVariable int id){
        UserModel user = userService.findByID(id);

        if(user == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
>>>>>>> Stashed changes
    }
}
