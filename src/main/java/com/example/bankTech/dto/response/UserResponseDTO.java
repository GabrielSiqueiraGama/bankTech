package com.example.bankTech.dto.response;

import com.example.bankTech.entities.user.UserType;

import java.math.BigDecimal;

public record UserResponseDTO(Long id, String fullname, String document, String email, BigDecimal ballance, UserType userType) {
}
