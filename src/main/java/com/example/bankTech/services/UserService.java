package com.example.bankTech.services;

import com.example.bankTech.dto.mapper.UserMapper;
import com.example.bankTech.entities.user.User;
import com.example.bankTech.entities.user.UserType;
import com.example.bankTech.exceptions.InsufficientBalanceException;
import com.example.bankTech.exceptions.TransactionNotAllowedException;
import com.example.bankTech.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void checkTransactionPermission(User sender, BigDecimal amount){
        if(sender.getUserType() == UserType.MERCHANT){
            throw new TransactionNotAllowedException("User type MERCHANT is not allowed to perform transactions");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException();
        }
    }
}
