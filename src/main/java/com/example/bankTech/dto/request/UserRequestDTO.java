package com.example.bankTech.dto.request;

import com.example.bankTech.entities.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotBlank String fullname, @NotBlank String document, @NotBlank @Email String email, @NotBlank @Size(min = 8, max = 60) String password, @NotNull  UserType userType) {
}
