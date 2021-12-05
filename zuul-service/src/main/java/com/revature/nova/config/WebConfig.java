package com.revature.nova.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .antMatchers("/user-service/Nova/login", "/user-service/Nova/register")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public ServerCodecConfigurer configurer(){
        return ServerCodecConfigurer.create();
    }
}
