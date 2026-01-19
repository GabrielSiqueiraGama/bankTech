package com.example.bankTech.services;

import com.example.bankTech.entities.user.User;
import com.example.bankTech.exceptions.TransactionNotAllowedException;
import com.example.bankTech.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositService {

    private final UserService userService;

    public DepositService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public void deposit(BigDecimal value, Long id){
        User user = userService.findEntityById(id);
        if(value != null && value.compareTo(BigDecimal.ZERO) > 0){
            user.setBalance(user.getBalance().add(value));
            userService.saveUser(user);
        }else{
            throw new TransactionNotAllowedException("Invalid value");
        }
    }
}
