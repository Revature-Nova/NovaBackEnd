package io.Nova.springsecurityjwt.services;

import io.Nova.springsecurityjwt.models.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationRequestService newService;

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {

        AuthenticationRequest newCustomer = newService.getCustomerByUserName(customerName);
        //creates a user with name and password
        return new User(newCustomer.getUsername(), newCustomer.getPassword(),
                new ArrayList<>());


    }


}
