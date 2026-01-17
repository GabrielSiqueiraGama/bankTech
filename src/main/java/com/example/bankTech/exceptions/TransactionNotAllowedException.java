package com.example.bankTech.exceptions;

public class TransactionNotAllowedException extends RuntimeException{

    public TransactionNotAllowedException(String message){
        super(message);
    }
}
