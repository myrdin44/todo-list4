package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.example.todolist.security.JwtService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
        userService.save(user);
        return "User created successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return "Token generated: " + jwtService.generateToken(user);
        }

        return "Invalid username or password!";
    }

}
