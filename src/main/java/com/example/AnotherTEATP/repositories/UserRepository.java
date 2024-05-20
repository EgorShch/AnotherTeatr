package com.example.AnotherTEATP.repositories;

import com.example.AnotherTEATP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
