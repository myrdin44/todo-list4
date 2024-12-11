package org.example.todolist.service;

import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public  User save(User user) {
        return userRepository.save(user);
    }

}
