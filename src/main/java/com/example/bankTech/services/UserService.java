package com.example.bankTech.services;

import com.example.bankTech.dto.mapper.UserMapper;
import com.example.bankTech.dto.request.UserRequestDTO;
import com.example.bankTech.dto.response.UserResponseDTO;
import com.example.bankTech.entities.user.User;
import com.example.bankTech.entities.user.UserType;
import com.example.bankTech.exceptions.InsufficientBalanceException;
import com.example.bankTech.exceptions.TransactionNotAllowedException;
import com.example.bankTech.exceptions.UserNotFoundException;
import com.example.bankTech.repositories.UserRepository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordService passwordService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordService = passwordService;
    }

    public void checkTransactionPermission(User sender, BigDecimal amount){
        if(sender.getUserType() == UserType.MERCHANT){
            throw new TransactionNotAllowedException("User type MERCHANT is not allowed to perform transactions");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException();
        }
    }

    public UserResponseDTO findById(Long id){
        return userRepository.findById(id).map(userMapper::toDTO).orElseThrow(()-> new UserNotFoundException(id));
    }

    public User findEntityById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userRequestDTO)));
    }

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
    
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
    	return userRepository.findById(id).map(userFunction ->{
    		userFunction.setFullname(userRequestDTO.fullname());
    		userFunction.setDocument(userRequestDTO.document());
    		userFunction.setEmail(userRequestDTO.email());
    		userFunction.setPassword(userRequestDTO.password());
    		userFunction.setUserType(userRequestDTO.userType());
    		return userRepository.save(userFunction);
    	}).map(userMapper::toDTO).orElseThrow(()-> new UserNotFoundException(id));
    }
    public void delete(Long id) {
    	if(! userRepository.existsById(id)) {throw new UserNotFoundException(id);}
    	userRepository.deleteById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

}
