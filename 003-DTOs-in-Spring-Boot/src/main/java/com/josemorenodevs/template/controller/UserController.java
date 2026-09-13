package com.josemorenodevs.template.controller;

import com.josemorenodevs.template.dto.CreateUserRequestDTO;
import com.josemorenodevs.template.dto.UserResponseDTO;
import com.josemorenodevs.template.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.josemorenodevs.template.service.UserService;
import com.josemorenodevs.template.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

    UserMapper userMapper;

    private final UserService userService;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        User user = userService.findById(id);

        return userMapper.toDTO(user);
    }

    @PostMapping
    public UserResponseDTO create(@Valid @RequestBody CreateUserRequestDTO request) {

        User user = userMapper.toEntity(request);

        user = userService.create(user);

        return userMapper.toDTO(user);
    }



}
