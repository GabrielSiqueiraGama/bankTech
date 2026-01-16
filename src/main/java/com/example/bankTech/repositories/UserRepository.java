package com.example.bankTech.repositories;

import com.example.bankTech.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDocument(String document);
}
