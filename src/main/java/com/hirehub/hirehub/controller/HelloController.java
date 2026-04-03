package com.hirehub.hirehub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Welcome to HireHub! Your Spring Boot backend is working!";
    }

    @GetMapping("/status")
    public String status() {
        return "Server is running successfully!";
    }
}