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

    // [6. OPTIONAL EN SPRING BOOT — 4:35]
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(id));
    }

    // Alternativa desde Java 10: orElseThrow() sin argumentos (lanza
    // NoSuchElementException)
    public User findByIdJava10(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    // ANTIPATRÓN [8. DÓNDE EVITAR OPTIONAL — 5:50]
    // Evita Optional como parámetro de método. Es preferible sobrecargar o pasar el
    // valor directo.
    // public void findUser(Optional<Long> id) { ... }
}
