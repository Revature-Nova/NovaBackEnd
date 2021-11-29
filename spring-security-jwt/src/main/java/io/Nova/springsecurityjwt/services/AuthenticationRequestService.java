package io.Nova.springsecurityjwt.services;

import io.Nova.springsecurityjwt.models.AuthenticationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class AuthenticationRequestService {

    public List<AuthenticationRequest> getAllCustomers(){

        List<AuthenticationRequest> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(new AuthenticationRequest("John", "Doe"));
        listOfCustomers.add(new AuthenticationRequest("Emma", "Wattson"));

        return listOfCustomers;
    }

    public AuthenticationRequest getCustomerByUserName(String userName){
        Predicate<AuthenticationRequest> byUserName = p -> p.getUsername().equals(userName);
        return filterCustomers(byUserName);
    }


    private AuthenticationRequest filterCustomers(Predicate<AuthenticationRequest> stratergy){
        return getAllCustomers().stream().filter(stratergy).findFirst().orElse(null);
    }

    public AuthenticationRequest addCustomer(AuthenticationRequest newCustomer) {
        return newCustomer;
    }
}
