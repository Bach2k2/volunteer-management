package com.bach2k2.volunteer.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bach2k2.volunteer.dto.ApiResponseDto;
import com.bach2k2.volunteer.dto.LoginRequestDto;
import com.bach2k2.volunteer.dto.RegisterRequestDto;

@Service
public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> login(LoginRequestDto loginRequest);
    ResponseEntity<ApiResponseDto<?>> register(RegisterRequestDto registerRequest);
    // ResponseEntity<ApiResponseDto<?>> logout();
}
