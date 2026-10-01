package com.josemorenodevs.swaggeropenapi.repository;

import com.josemorenodevs.swaggeropenapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
