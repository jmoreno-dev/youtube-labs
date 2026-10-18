package com.josemorenodevs.optional.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.josemorenodevs.optional.entity.User;
import com.josemorenodevs.optional.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User("jose", "jose@email.com", "+34600112233"));
    }
}
