package com.josemorenodevs.template.repository;

import com.josemorenodevs.template.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
