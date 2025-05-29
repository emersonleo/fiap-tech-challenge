package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controllers.handlers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.exceptions.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.exceptions.DadoInvalidoException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.exceptions.FalhaLoginException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.exceptions.ProprietarioNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Erro de validação: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse response = new ErrorResponse(
                errors.toString(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name()
        );

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(ClienteNotFoundException ex) {
        log.warn("Cliente não encontrado: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name()
        );

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(ProprietarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProprietarioNotFoundException(ProprietarioNotFoundException ex) {
        log.warn("Proprietário não encontrado: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name()
        );

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(FalhaLoginException.class)
    public ResponseEntity<ErrorResponse> handleFalhaLoginException(FalhaLoginException ex) {
        log.error("Falha de login: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name()
        );

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        log.error("Erro de integridade de dados: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                "Erro de integridade de dados! Verifique os dados fornecidos.",
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name()
        );

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @ExceptionHandler(DadoInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleDadoInvalidoException(DadoInvalidoException ex) {
        log.warn("Dado inválido: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name()
        );

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
