package com.revature.nova;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Nova")
public class PingController {
    @GetMapping(value = "/ping")
    public String ping(){
        return "Pong!";
    }
}
