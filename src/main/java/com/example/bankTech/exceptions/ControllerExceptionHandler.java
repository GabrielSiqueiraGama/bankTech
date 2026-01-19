package com.example.bankTech.exceptions;

import com.example.bankTech.dto.response.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity DuplicateEntry(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Duplicate information", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler
    public ResponseEntity e404(EntityNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Entity Not Found", "404");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
