package com.example.bankTech.repositories;

import com.example.bankTech.dto.request.UserRequestDTO;
import com.example.bankTech.entities.user.User;
import com.example.bankTech.entities.user.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    public UserRepositoryTest(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("Should return sucessfully")
    void findUserByDocumentSucess() {
        String document = "61639116439";

        UserRequestDTO user = new UserRequestDTO("Bluiz L", document, "siqueiragamagabriel@gmail.com", "1234567898", UserType.COMMON);
        this.createUser(user);

        Optional<User> foundedUser = this.userRepository.findByDocument(document);
        assertThat(foundedUser.isPresent());
    }

    @Test
    @DisplayName("Should not return user")
    void findUserByDocumentError() {
        String document = "61639116439";

        Optional<User> foundedUser = this.userRepository.findByDocument(document);
        assertThat(foundedUser.isEmpty());
    }

    private User createUser(UserRequestDTO userDTO){
        User user  = new User();
        user.setFullname(userDTO.fullname());
        user.setDocument(userDTO.document());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setUserType(userDTO.userType());
        this.entityManager.persist(user);
        return user;
    }



}

