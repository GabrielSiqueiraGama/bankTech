package com.example.bankTech.controllers;

import com.example.bankTech.dto.request.TransactionRequestDTO;
import com.example.bankTech.dto.response.TransactionResponseDTO;
import com.example.bankTech.entities.transaction.Transaction;
import com.example.bankTech.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> doTransaction(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
        Transaction transaction = this.transactionService.createTransaction(transactionRequestDTO);
        return new ResponseEntity<>(transaction , HttpStatus.OK);
    }
}
