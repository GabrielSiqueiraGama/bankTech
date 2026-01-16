package com.example.bankTech.dto.mapper;

import com.example.bankTech.dto.request.UserRequestDTO;
import com.example.bankTech.dto.response.UserResponseDTO;
import com.example.bankTech.entities.User;
import com.example.bankTech.entities.UserType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserMapper {
    public UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.getId(), user.getFullname(), user.getDocument(),
                user.getEmail(), user.getBallance(), user.getUserType());
    }

    public User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setFullname(userRequestDTO.fullname());
        user.setEmail(userRequestDTO.email());
        user.setDocument(userRequestDTO.document());
        user.setPassword(userRequestDTO.password());
        user.setUserType(userRequestDTO.userType());
        return user;
    }
}
