package com.example.bankTech.dto.request;

import com.example.bankTech.entities.UserType;

public record UserRequestDTO(String fullname, String document, String email, String password, UserType userType) {
}
