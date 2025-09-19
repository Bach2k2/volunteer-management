package com.bach2k2.volunteer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bach2k2.volunteer.model.User;

@Service
public interface UserService{
    void save(User user);
    ResponseEntity<User> getUser(Long userId);
    ResponseEntity<List<User>> getAllUsers();
    void deleteUser(Long id);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
    
}