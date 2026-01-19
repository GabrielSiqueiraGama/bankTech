package com.example.bankTech.controllers;

import com.example.bankTech.services.DepositService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/{id}")
    public void deposit(@RequestParam BigDecimal value, @PathVariable Long id){
        depositService.deposit(value, id);
    }
}
