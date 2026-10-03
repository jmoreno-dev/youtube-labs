package com.josemorenodevs.validations.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Min (21)
        Integer age){
}
