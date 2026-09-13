package com.josemorenodevs.template.mapper;

import com.josemorenodevs.template.dto.CreateUserRequestDTO;
import com.josemorenodevs.template.dto.UserResponseDTO;
import com.josemorenodevs.template.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public User toEntity(CreateUserRequestDTO request) {

        User user = new User();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return user;
    }
}
