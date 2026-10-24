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

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(id));
    }


}