package com.example.bankTech.config;

import com.example.bankTech.entities.UserType;
import com.example.bankTech.repositories.UserTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {
    //DataLoader will initialize the database at creation time.
    //DataLoader vai inicializar o banco de dados no momento da criação
    private final UserTypeRepository userTypeRepository;

    public DataLoader(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(UserType.enumUserType.values())
                .forEach(userType-> userTypeRepository.save(userType.get()));
    }
}
