package com.example.bankTech.services;

import com.example.bankTech.dto.mapper.TransactionMapper;
import com.example.bankTech.dto.mapper.UserMapper;
import com.example.bankTech.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

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
    private final TransactionService transactionService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    public TransactionServiceTest(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Test
    void createTransaction() {
    }
}