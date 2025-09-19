package com.bach2k2.volunteer.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bach2k2.volunteer.model.User;
import com.bach2k2.volunteer.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        System.err.println("Loading user by username: " + username + ", found: " + optionalUser.isPresent());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(optionalUser.get());
    }

    public UserDetails loadUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
        return new CustomUserDetails(optionalUser.get());
    }

}
