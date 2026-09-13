package com.josemorenodevs.template.config;

import com.josemorenodevs.template.entity.User;
import com.josemorenodevs.template.repository.UserRepository;
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
        User user = new User("jose", "jose@email.com", "123456");
        userRepository.save(user);
    }
}
