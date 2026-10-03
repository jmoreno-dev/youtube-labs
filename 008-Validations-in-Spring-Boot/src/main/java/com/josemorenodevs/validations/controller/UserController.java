package com.josemorenodevs.validations.controller;

import com.josemorenodevs.validations.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserRequest> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(request);
    }
}
