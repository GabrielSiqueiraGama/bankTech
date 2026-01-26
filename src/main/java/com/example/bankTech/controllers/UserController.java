package com.example.bankTech.controllers;

import com.example.bankTech.dto.request.UserRequestDTO;
import com.example.bankTech.dto.response.UserResponseDTO;
import com.example.bankTech.messages.FailureResponse;
import com.example.bankTech.repositories.UserRepository;
import com.example.bankTech.services.PasswordService;
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
    private final PasswordService passwordService;
    public UserController(UserService userService, PasswordService passwordService) {
        this.passwordService = passwordService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO){
        List<String> failures = passwordService.listOfFails(userRequestDTO.password());

        if (!failures.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new FailureResponse(failures));
        }

        UserResponseDTO user = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
