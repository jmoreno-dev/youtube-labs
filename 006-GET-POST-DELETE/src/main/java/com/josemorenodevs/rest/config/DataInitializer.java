package com.josemorenodevs.rest.config;

import com.josemorenodevs.rest.entity.User;
import com.josemorenodevs.rest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
