package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.example.todolist.security.JwtService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String userName = body.get("userName");
        String password = body.get("password");
        UserDetails userDetails = userService.findByUsername(userName);

        if(userDetails != null && userDetails.getPassword().equals(password)) {
            return ResponseEntity.ok("Token generated: " + jwtService.generateToken(userDetails));
        }

        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
}
