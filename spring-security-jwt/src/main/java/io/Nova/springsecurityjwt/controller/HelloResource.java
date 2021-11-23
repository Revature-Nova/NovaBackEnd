package io.Nova.springsecurityjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
    //this is the end point to be reached through authentication
    @RequestMapping({"/"})
    public String rootPage(){
        return "Root Page";
    }

    @RequestMapping({"/hello"})
    public String hello(){
        return "Hello World";
    }
}

