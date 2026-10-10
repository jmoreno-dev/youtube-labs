package com.josemorenodevs.lombok.repository;

import com.josemorenodevs.lombok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
