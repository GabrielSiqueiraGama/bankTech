package com.example.bankTech.config;

import com.example.bankTech.entities.WalletType;
import com.example.bankTech.repositories.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {
    //DataLoader will initialize the database at creation time.
    //DataLoader vai inicializar o banco de dados no momento da criação
    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.enumWalletType.values())
                .forEach(walletType-> walletTypeRepository.save(walletType.get()));
    }
}
