package io.Nova.springsecurityjwt.controller;

import io.Nova.springsecurityjwt.models.AuthenticationRequest;
import io.Nova.springsecurityjwt.models.AuthenticationResponse;
import io.Nova.springsecurityjwt.services.AuthenticationRequestService;
import io.Nova.springsecurityjwt.services.MyUserDetailsService;
import io.Nova.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    AuthenticationRequestService customerService;

    //this is the end point to be reached through authentication
    @RequestMapping({"/"})
    public String rootPage(){
        return "Root Page";
    }

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    /* Get a list of all customers*/
    @GetMapping("/customers")
    public List<AuthenticationRequest> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    /*  get a customer by the username */
    @GetMapping("/customer/{username}")
    public ResponseEntity<AuthenticationRequest> getCustomerByUsername(@PathVariable("username") String customerUserName){

        AuthenticationRequest customer = customerService.getCustomerByUserName(customerUserName);
        if(customer == null){
            return new ResponseEntity<AuthenticationRequest> (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AuthenticationRequest> (customer, HttpStatus.OK);
    }


    /* Adding a new customer */
    @CrossOrigin
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationRequest addCustomer(@RequestBody AuthenticationRequest newCustomer){
        return customerService.addCustomer(newCustomer);
    }


    /* gets the username and password from the request map */
    @CrossOrigin
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        /* Authenticate with the username and password given */
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        /* Grabs the user details  */
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        /* I pass in the user details to create a token */
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        /* Responds with 200 ok, and the payload is going to be the Authentication response class */
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

