package org.example.todolist.service;

import org.example.todolist.model.User;
import org.example.todolist.repository.UserRepository;
import org.example.todolist.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }

        return false;
    }

    public User updateUser(long userId, User newUser) {
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).orElse(null);
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setFullName(newUser.getFullName());
            user.setPhone(newUser.getPhone());
            user.setRole(newUser.getRole());
            user.setFalcultyOrDepartment(newUser.getFalcultyOrDepartment());
            user.setSubject(newUser.getSubject());
            user.setSystemLevel(newUser.getSystemLevel());
            user.setStatus(newUser.getStatus());
            user.setDegreeName(newUser.getDegreeName());

            return userRepository.save(user);
        }
        return null;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getUserByFalcultyOrDepartment(String department) {
        return userRepository.findByFalcultyOrDepartment(department);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
