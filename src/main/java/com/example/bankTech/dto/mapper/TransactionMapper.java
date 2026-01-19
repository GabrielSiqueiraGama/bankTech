package com.example.bankTech.dto.mapper;

import com.example.bankTech.dto.request.TransactionRequestDTO;
import com.example.bankTech.dto.response.TransactionResponseDTO;
import com.example.bankTech.entities.transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponseDTO toDTO(Transaction transaction){
        return new TransactionResponseDTO(transaction.getAmount(),
                transaction.getSender().getId(),
                transaction.getReceiver().getId());
    }

    public Transaction toEntity(TransactionRequestDTO transactionRequestDTO){
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequestDTO.amount());
        transaction.setSender(transactionRequestDTO.sender());
        transaction.setReceiver(transactionRequestDTO.receiver());
        transaction.setTimestamp(transactionRequestDTO.timestamp());
        return transaction;
    }
}
