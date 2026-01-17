package com.example.bankTech.exceptions;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {

        super("Insufficient balance.");
    }
}
