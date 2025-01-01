package org.example.todolist.controller;
import org.example.todolist.model.User;
import org.example.todolist.security.JwtException;
import org.example.todolist.security.JwtService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operate")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @DeleteMapping("/delete/{userId}")
    public boolean deleteUser(@PathVariable("userId") long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/update-user")
    public User updateUser(@RequestParam long userId, @RequestBody User newUser) {
        return userService.updateUser(userId, newUser);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Authorization header.");
        }
        String jwtToken = authHeader.substring(7); // Bỏ "Bearer " để lấy token

        try {
            if (jwtService.isTokenValid(jwtToken)) {
                String username = jwtService.extractUsername(jwtToken);
                return ResponseEntity.ok().body("Token is valid. Username: " + username);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
            }
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed: " + e.getMessage());
        }
    }

    @GetMapping("/get-user-by-faculty/{faculty}")
    public List<User> getUserByFaculty(@PathVariable("faculty") String faculty) {
        return userService.getUserByFalcultyOrDepartment(faculty);
    }

    @GetMapping("/get-all-user")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

}
