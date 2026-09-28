package com.josemorenodevs.template.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.josemorenodevs.template.entity.User;
import com.josemorenodevs.template.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        User user = new User("jose", "jose@email.com");
        userRepository.save(user);
    }
}
