package com.example.AdminBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class firstclass {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Deepika! Your backend is working 🎉";
    }
}
