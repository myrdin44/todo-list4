package org.example.todolist.service;

import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new CustomUserDetails(user);
    }

    public UserDetails findByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        return new CustomUserDetails(user);
    }

    public  User save(User user) {
        return userRepository.save(user);
    }

}
