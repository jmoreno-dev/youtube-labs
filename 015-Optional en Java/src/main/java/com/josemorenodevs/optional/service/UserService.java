package com.josemorenodevs.optional.service;

import org.springframework.stereotype.Service;

import com.josemorenodevs.optional.entity.User;
import com.josemorenodevs.optional.exception.UserNotFoundException;
import com.josemorenodevs.optional.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // [6. OPTIONAL IN SPRING BOOT — 4:35]
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(id));
    }

    // Java 10+ alternative: orElseThrow() with no arguments (throws
    // NoSuchElementException)
    public User findByIdJava10(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    // ANTIPATTERN
    // Avoid Optional as method parameters. Overload or pass the value directly
    // instead.
    // public void findUser(Optional<Long> id) { ... }
}