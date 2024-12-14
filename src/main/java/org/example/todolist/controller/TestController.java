package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> testSecureEndpoint() {
        return ResponseEntity.ok("Secure endpoint accessed successfully");
    }
}
