package com.josemorenodevs.template.mapper;

import com.josemorenodevs.template.dto.UserResponseDTO;
import com.josemorenodevs.template.entity.User;

public class UserMapper {

    public UserResponseDTO toResponseDto(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
