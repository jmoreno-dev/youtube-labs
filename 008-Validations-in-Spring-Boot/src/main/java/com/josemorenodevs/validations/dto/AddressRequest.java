package com.josemorenodevs.validations.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank
        String street,
        @NotBlank
        String city) {
}
