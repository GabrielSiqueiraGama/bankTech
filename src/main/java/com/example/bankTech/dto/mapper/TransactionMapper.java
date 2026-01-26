package com.example.bankTech.dto.mapper;

import com.example.bankTech.dto.response.TransactionResponseDTO;
import com.example.bankTech.entities.transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponseDTO toDTO(Transaction transaction){
        return new TransactionResponseDTO(transaction.getSender().getId(),
                transaction.getReceiver().getId(),transaction.getAmount()
                );
    }
}
