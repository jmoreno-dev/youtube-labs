package com.josemorenodevs.lombok.config;

import com.josemorenodevs.lombok.entity.User;
import com.josemorenodevs.lombok.repository.UserRepository;
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
        User user2 = new User("jose2", "jose2@email.com");
        userRepository.save(user2);
    }
}
