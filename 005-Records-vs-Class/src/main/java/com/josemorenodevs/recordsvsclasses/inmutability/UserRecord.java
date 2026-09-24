package com.josemorenodevs.recordsvsclasses.inmutability;

import java.util.List;

public record UserRecord(
        Long id,
        String name,
        List<String> roles
) {
}
