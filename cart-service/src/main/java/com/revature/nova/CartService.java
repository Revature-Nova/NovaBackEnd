package com.revature.nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CartService {

	public static void main(String[] args) {
		SpringApplication.run(CartService.class, args);
	}

}
