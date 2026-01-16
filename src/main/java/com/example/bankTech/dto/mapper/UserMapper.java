package com.example.bankTech.dto.mapper;

import com.example.bankTech.dto.request.UserRequestDTO;
import com.example.bankTech.dto.response.UserResponseDTO;
import com.example.bankTech.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDTO(User user){
        return new UserResponseDTO(user.getId(), user.getFullname(), user.getDocument(),
                user.getEmail(), user.getBalance(), user.getUserType());
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
