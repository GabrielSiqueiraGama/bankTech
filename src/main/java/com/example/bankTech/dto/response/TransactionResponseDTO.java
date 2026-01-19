package com.example.bankTech.dto.response;

import java.math.BigDecimal;

public record TransactionResponseDTO(Long sender_id, Long receiver_id, BigDecimal amount ) {
}
