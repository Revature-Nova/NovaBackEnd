package com.revature.nova.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * The spring security config for web security
 *
 * @date 11/22/2021
 * @author Emmanuel Tejeda, James Brown, Kollier Martin
 */
@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("**/register/**","**/login/**")
                    .permitAll()
                .antMatchers("/Nova/**")
                    .authenticated();
    }

    @Bean
    public ServerCodecConfigurer configurer(){
        return ServerCodecConfigurer.create();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setExposedHeaders(Arrays.asList("Response-Type", "Content-Type", "Authorization", "authorization", "Authentication"));
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Response-Type", "Content-Type", "Authorization", "authorization", "Authentication"));
        config.setAllowedMethods(Arrays.asList("POST", "GET", "OPTIONS", "DELETE", "PUT", "PATCH"));

        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
