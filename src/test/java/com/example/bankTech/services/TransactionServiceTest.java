package com.example.bankTech.services;

import com.example.bankTech.dto.mapper.TransactionMapper;
import com.example.bankTech.dto.mapper.UserMapper;
import com.example.bankTech.dto.request.TransactionRequestDTO;
import com.example.bankTech.entities.user.User;
import com.example.bankTech.entities.user.UserType;
import com.example.bankTech.exceptions.TransactionNotAllowedException;
import com.example.bankTech.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("Should create transaction sucessfully")
    void createTransactionSucess() {
        User sender = new User(1L, "Gabriel Silva", "gabriel.silva@email.com", "12345678901", "123456", new BigDecimal("1000.00"), UserType.COMMON);
        User receiver = new User(2L, "Ana Costa", "ana.costa@email.com", "98765432100", "senha123", new BigDecimal("2500.00"), UserType.COMMON);

        when(userService.findEntityById(1L)).thenReturn(sender);
        when(userService.findEntityById(2L)).thenReturn(receiver);

        when(authorizationService.authorizateTransaction(any(), any())).thenReturn(true);

        TransactionRequestDTO transaction = new TransactionRequestDTO(1L, 2L, new BigDecimal(10.00), LocalDateTime.now());
        this.transactionService.createTransaction(transaction);

        verify(transactionRepository, times(1)).save(any());

        sender.setBalance(new BigDecimal(990.00));
        verify(userService, times(1)).saveUser(sender);
        receiver.setBalance(new BigDecimal(2510.00));
        verify(userService, times(1)).saveUser(receiver);
    }

    @Test
    @DisplayName("Should not create transaction")
    void createTransactionError() {
        User sender = new User(1L, "Gabriel Silva", "gabriel.silva@email.com", "12345678901", "123456", new BigDecimal("1000.00"), UserType.COMMON);
        User receiver = new User(2L, "Ana Costa", "ana.costa@email.com", "98765432100", "senha123", new BigDecimal("2500.00"), UserType.COMMON);

        when(userService.findEntityById(1L)).thenReturn(sender);
        when(userService.findEntityById(2L)).thenReturn(receiver);

        when(authorizationService.authorizateTransaction(any(), any())).thenReturn(false);
        Exception thrown = Assertions.assertThrows(TransactionNotAllowedException.class, ()->{
            TransactionRequestDTO transaction = new TransactionRequestDTO(1L, 2L, new BigDecimal(10.00), LocalDateTime.now());
            this.transactionService.createTransaction(transaction);
        });

        Assertions.assertEquals("Transaction not allowed. Server does not allow transfers at the moment. Please try again later.", thrown.getMessage());
    }
}