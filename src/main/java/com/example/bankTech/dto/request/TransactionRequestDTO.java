package com.example.bankTech.dto.request;

import com.example.bankTech.entities.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO(BigDecimal amount, User sender, User receiver, LocalDateTime timestamp) {
}
