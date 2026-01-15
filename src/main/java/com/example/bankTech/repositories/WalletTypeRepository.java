package com.example.bankTech.repositories;

import com.example.bankTech.entities.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
