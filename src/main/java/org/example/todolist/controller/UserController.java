package org.example.todolist.controller;
import org.example.todolist.model.User;
import org.example.todolist.security.JwtException;
import org.example.todolist.security.JwtService;
import org.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operate")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    //delete an user
    @DeleteMapping("/delete/{userId}")
    public boolean deleteUser(@PathVariable("userId") long userId) {
        return userService.deleteUser(userId);
    }

    //update an user's information
    @PutMapping("/update-user")
    public User updateUser(@RequestParam long userId, @RequestBody User newUser) {
        return userService.updateUser(userId, newUser);
    }

    //
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

    //filter users by their faculty
    @GetMapping("/get-user-by-faculty/{faculty}")
    public List<User> getUserByFaculty(@PathVariable("faculty") String faculty) {
        return userService.getUserByFacultyOrDepartment(faculty);
    }

    //get an user by their id
    @GetMapping("/get-user")
    public User getUerById(@RequestParam("userId") long userId) {
        return userService.getUserById(userId);
    }

    //get all users in database, split into pages
    @GetMapping("/get-all-user")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        return userService.getAllUsers(page, size);
    }

    //get
    @GetMapping("/current-info")
    public ResponseEntity<?> getCurrentUserInfo() {
        UserDetails userDetails = userService.getLoggedUser();

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user logged in.");
        }

        User user = userService.getUserByUserName(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    //search users by their name
    @PostMapping("/filter-by-name")
    public Page<User> filterUser(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.filterUserByName(name, page, size);
    }

    //return faculty enum
    @GetMapping("/faculty-list")
    public ResponseEntity<List<String>> getFacultyList() {
        return ResponseEntity.ok().body(userService.getFacultyList());
    }

    //return degree enum
    @GetMapping("/degree-list")
    public ResponseEntity<List<String>> getDegreeList() {
        return ResponseEntity.ok().body(userService.getDegreeList());
    }

}
