package com.josemorenodevs.rest.repository;

import com.josemorenodevs.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
