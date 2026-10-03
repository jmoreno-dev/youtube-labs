package com.josemorenodevs.validations.controller;

import com.josemorenodevs.validations.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest request) {

        if (request.name() == null || request.name().isBlank()) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        if (request.email() == null || !request.email().contains("@")) {
            return ResponseEntity.badRequest().body("Email is not valid");
        }

        if (request.age() == null || request.age() < 21) {
            return ResponseEntity.badRequest().body("Minimum age is 21");
        }

        return ResponseEntity.ok("User created successfully");
    }
}
