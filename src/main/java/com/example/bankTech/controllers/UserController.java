package com.example.bankTech.controllers;

import com.example.bankTech.dto.request.UserRequestDTO;
import com.example.bankTech.dto.response.UserResponseDTO;
import com.example.bankTech.repositories.UserRepository;
import com.example.bankTech.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO user = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
