package com.revature.nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CartDriver {

	public static void main(String[] args) {
		SpringApplication.run(CartDriver.class, args);
		corsFilter();
	}

	@Bean
	public static CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		final List<String> headers = Arrays.asList("Access-Control-Allow-Headers", "Response-Type", "Content-Type", "Authorization", "authorization", "Authentication", "Key");
		final List<String> methods = Arrays.asList("OPTIONS", "HEAD", "GET", "PUT", "POST", "PATCH", "DELETE");

		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.setAllowedMethods(methods);
		config.setAllowedHeaders(headers);

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
