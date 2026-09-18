package com.josemorenodevs.recordsvsclasses.valueobject;

public record Email(String value) {

    public Email {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
    }
}
