package com.example.bankTech.repositories;

import com.example.bankTech.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
