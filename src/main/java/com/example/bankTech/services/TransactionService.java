package com.example.bankTech.services;

import com.example.bankTech.dto.mapper.TransactionMapper;
import com.example.bankTech.dto.mapper.UserMapper;
import com.example.bankTech.dto.request.TransactionRequestDTO;
import com.example.bankTech.dto.response.TransactionResponseDTO;
import com.example.bankTech.dto.response.UserResponseDTO;
import com.example.bankTech.entities.transaction.Transaction;
import com.example.bankTech.entities.user.User;
import com.example.bankTech.exceptions.TransactionNotAllowedException;
import com.example.bankTech.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final RestTemplate restTemplate;

    public TransactionService(UserService userService, UserMapper userMapper, TransactionRepository transactionRepository, TransactionMapper transactionMapper, RestTemplate restTemplate) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void createTransaction(TransactionResponseDTO transactionResponseDTO){
        User sender = userService.findEntityById(transactionResponseDTO.sender_id());
        User receiver = userService.findEntityById(transactionResponseDTO.receiver_id());

        userService.checkTransactionPermission(sender, transactionResponseDTO.value());

        boolean isAuthorizated = this.authorizateTransaction(sender, transactionResponseDTO.value());

        if(!isAuthorizated){
            throw new TransactionNotAllowedException("Transaction not allowed. " +
                    "Server does not allow transfers at the moment. " +
                    "Please try again later.");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionResponseDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionResponseDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionResponseDTO.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizateTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> response =  restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            String status = (String) response.getBody().get("status");
            return "success".equalsIgnoreCase(status);
        }else return false;
    }
}
