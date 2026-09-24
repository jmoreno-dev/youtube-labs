package com.josemorenodevs.recordsvsclasses.inmutability;

import java.util.ArrayList;
import java.util.List;

public class RolesDemo {

    public static void main(String[] args) {

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        UserRecord user = new UserRecord(
                1L,
                "Jose",
                roles
        );

        roles.add("ADMIN");

        System.out.println(user.roles());
    }
}
