package com.revature.nova.controllers;

import com.revature.nova.models.Cart;
import com.revature.nova.models.UserModel;
import com.revature.nova.repositories.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(name = "cart")
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() { return new RestTemplate();}

    @Autowired
    private  RestTemplate restTemplate;

    @GetMapping(name = "/{userId}")
    public Cart getUserCart(@PathVariable int userId){

        UserModel user = this.restTemplate.getForObject("http://user-service/user/"+userId, UserModel.class);
        
        return this.cartRepo.getById(user.getUserID());
    }
}
