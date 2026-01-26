package com.example.bankTech.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO(Long  sender, Long  receiver, BigDecimal amount, LocalDateTime timestamp) {
}
