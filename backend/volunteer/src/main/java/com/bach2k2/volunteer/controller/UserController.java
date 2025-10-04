package com.bach2k2.volunteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bach2k2.volunteer.dto.ApiResponseDto;
import com.bach2k2.volunteer.model.User;
import com.bach2k2.volunteer.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "User Management", description = "User Management APIs")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
// @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8081"}, 
//              allowedHeaders = "*", 
//              methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
// @SecurityRequirement(name = "Bearer Authentication") 
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("")
    public ResponseEntity<ApiResponseDto<List<User>>> getAllUser() {
        return ResponseEntity.ok(new ApiResponseDto<>(200, "Success", userService.getAllUsers().getBody()));
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by their ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<User>> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponseDto<>(200, "Success", userService.getUser(id).getBody()));
    }

    @Operation(summary = "Create new user", description = "Create a new user in the system")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("")
    public ResponseEntity<ApiResponseDto<User>> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(new ApiResponseDto<>(201, "User created successfully", user));
    }
    
    @Operation(summary = "Delete user", description = "Delete a user by their ID")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponseDto<>(200, "User deleted successfully", null));
    }
    
    @Operation(summary = "Update user", description = "Update an existing user's information")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.save(user);
        return ResponseEntity.ok(new ApiResponseDto<>(200, "User updated successfully", user));
    }
    
}
