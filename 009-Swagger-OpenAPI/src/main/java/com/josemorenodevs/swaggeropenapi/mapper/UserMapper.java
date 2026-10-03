package com.josemorenodevs.swaggeropenapi.mapper;

import org.springframework.stereotype.Component;

import com.josemorenodevs.swaggeropenapi.dto.UserRequestDTO;
import com.josemorenodevs.swaggeropenapi.dto.UserResponseDTO;
import com.josemorenodevs.swaggeropenapi.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        return new User(dto.username(), dto.email());
    }

    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
