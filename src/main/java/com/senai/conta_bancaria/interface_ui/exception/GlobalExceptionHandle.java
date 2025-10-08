package com.senai.conta_bancaria.interface_ui.exception;
import com.senai.conta_bancaria.domain.entity.exceptions.ValoresNegativoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(ValoresNegativoException.class)
    public ResponseEntity<String> handleValoresNegativoException(ValoresNegativoException ex) {
        return ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
