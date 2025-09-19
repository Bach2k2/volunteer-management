package com.bach2k2.volunteer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bach2k2.volunteer.configurations.jwt.JwtTokenProvider;
import com.bach2k2.volunteer.dto.ApiResponseDto;
import com.bach2k2.volunteer.dto.LoginRequestDto;
import com.bach2k2.volunteer.dto.LoginResponseDto;
import com.bach2k2.volunteer.dto.RegisterRequestDto;
import com.bach2k2.volunteer.model.User;
import com.bach2k2.volunteer.service.AuthService;
import com.bach2k2.volunteer.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        private UserService userService;

        @Autowired
        JwtTokenProvider jwtTokenProvider;
        
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public ResponseEntity<ApiResponseDto<?>> login(LoginRequestDto loginRequest) {
                // Implement your login logic here
                try {
                        Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        loginRequest.getUsername(),
                                                        loginRequest.getPassword()));
                        System.out.println("Generated Token:" + authentication);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        String token = jwtTokenProvider.generateJwtToken(loginRequest.getUsername());
                        System.out.println("Generated Token: " + token);
                        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                        List<String> roles = userDetails.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList());

                        LoginResponseDto loginResponseDto = new LoginResponseDto(token);
                        return ResponseEntity.ok(new ApiResponseDto<LoginResponseDto>(200, "Login successful",
                                        loginResponseDto));
                } catch (BadCredentialsException e) {
                        // e.printStackTrace();
                        System.out.println("Login failed for user: " + loginRequest.getUsername());
                        return ResponseEntity.badRequest()
                                        .body(new ApiResponseDto<>(400, "Invalid username or password"));
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                        return ResponseEntity.status(500)
                                        .body(new ApiResponseDto<>(500, "An error occurred during login")); 
                }
        }

        public ResponseEntity<ApiResponseDto<?>> register(RegisterRequestDto registerRequest) {
                User user = createUserIfNotExist(registerRequest);
                userService.save(user);
                return ResponseEntity.ok(new ApiResponseDto<User>(200, "Register successful", user));
        }

        private User createUserIfNotExist(RegisterRequestDto registerRequest) {
                return new User(registerRequest.getEmail(), registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()), String.join(",", registerRequest.getRoles()));      
        }
}
