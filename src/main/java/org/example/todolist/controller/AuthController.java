package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.example.todolist.security.JwtService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://bigdragonusermanagementapp.netlify.app")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public String createUser(@RequestBody User user) {
        userService.save(user);
        return "Registered Successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        UserDetails userDetails = userService.findByUsername(userName);

        if(userDetails != null && userDetails.getPassword().equals(password)) {
            return "Token generated: " + jwtService.generateToken(userDetails);
        }

        return "Invalid username or password!";
    }
}
