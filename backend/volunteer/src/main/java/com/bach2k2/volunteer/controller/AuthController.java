package com.bach2k2.volunteer.controller;

// import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bach2k2.volunteer.dto.ApiResponseDto;
import com.bach2k2.volunteer.dto.LoginRequestDto;
import com.bach2k2.volunteer.dto.RegisterRequestDto;
import com.bach2k2.volunteer.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Authentication APIs")
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8081"}, 
             allowedHeaders = "*", 
             methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/v1/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<?>> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        return authService.login(loginRequest);
        
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<?>> register(@RequestBody @Valid RegisterRequestDto registerRequest) {
        return authService.register(registerRequest);
    }
}
 