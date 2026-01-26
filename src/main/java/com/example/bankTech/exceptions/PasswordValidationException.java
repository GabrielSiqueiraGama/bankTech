package com.example.bankTech.exceptions;

import java.util.List;

public class PasswordValidationException extends RuntimeException {

    private final List<String> failures;

    public PasswordValidationException(List<String> failures) {
        this.failures = failures;
    }

    public List<String> getFailures() {
        return failures;
    }
}