package com.example.bankTech.services;

import com.example.bankTech.entities.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizationService {

    private final RestTemplate restTemplate;

    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean authorizateTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> response =  restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            String status = (String) response.getBody().get("status");
            return "success".equalsIgnoreCase(status);
        }else return false;
    }
}

