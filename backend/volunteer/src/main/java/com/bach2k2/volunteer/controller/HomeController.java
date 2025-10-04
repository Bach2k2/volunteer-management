package com.bach2k2.volunteer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Home", description = "Home APIs")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Volunteer Management System";
    }

    @GetMapping("/api/secure/{param}")
    public String secureEndString(@PathVariable("param") String param) {
        return "Secure endpoint accessed with param: " + param;
    }

    @GetMapping("/api/public/{param}")
    public String publicEndString(@PathVariable("param") String param) {
        return "Public endpoint accessed with param: " + param;
    }

    @GetMapping("/api/public1/1")
    public String publicEndString1(@RequestParam(name = "param") String param) {
        return "Public endpoint accessed with param: " + param;
    }

}
