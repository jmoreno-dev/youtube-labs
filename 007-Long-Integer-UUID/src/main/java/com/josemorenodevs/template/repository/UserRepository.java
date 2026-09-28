package com.josemorenodevs.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josemorenodevs.template.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
