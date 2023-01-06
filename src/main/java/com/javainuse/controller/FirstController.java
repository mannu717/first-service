package com.javainuse.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class FirstController {

    @GetMapping("/message")
    public String test(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        return "Hello JavaInUse Called in First Service: " + auth;
    }

    //    @PostMapping(value = "/message", consumes = "text/plain")
    @PostMapping(value = "/message", consumes = "application/json")
    public String testPost(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody String body) {
        return "Hello JavaInUse Called in First Service: " + auth + " Body: " + body;
    }
}
