package com.example.bankTech.repositories;

import com.example.bankTech.entities.user.User;
import com.example.bankTech.entities.user.UserType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDocument(String document);
    
    List<User> findByUserType(UserType userType);
}
