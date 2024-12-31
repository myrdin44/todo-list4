package org.example.todolist.controller;

import org.example.todolist.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-possible-deployment")
public class TestController {

    @GetMapping("/is-deployed")
    public ResponseEntity<String> testSecureEndpoint() {
        return ResponseEntity.ok("Possible deployment!");
    }
}
