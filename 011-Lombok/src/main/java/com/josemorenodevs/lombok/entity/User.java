package com.josemorenodevs.lombok.entity;

import lombok.*;

@Data
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
