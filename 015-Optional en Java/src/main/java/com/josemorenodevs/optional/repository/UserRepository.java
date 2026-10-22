package com.josemorenodevs.optional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josemorenodevs.optional.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
