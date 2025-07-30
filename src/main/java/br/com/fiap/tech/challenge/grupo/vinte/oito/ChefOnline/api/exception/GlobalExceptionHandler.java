package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api.exception;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente.ClienteJaExisteException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario.ProprietarioJaExisteException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario.ProprietarioNotFoundException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            validationErrors.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponse errorResponse = ErrorResponse.withDetails(
            "VALIDATION_ERROR", 
            "Dados de entrada inválidos", 
            validationErrors, 
            status
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler({ClienteNotFoundException.class, ProprietarioNotFoundException.class})
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(CoreException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(ErrorResponse.fromCoreException(ex, status));
    }

    @ExceptionHandler({ClienteJaExisteException.class, ProprietarioJaExisteException.class})
    @ApiResponse(responseCode = "409", description = "Recurso já existe")
    public ResponseEntity<ErrorResponse> handleConflictExceptions(CoreException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(ErrorResponse.fromCoreException(ex, status));
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        HttpStatus genericStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String genericMessage = String.format("Erro interno do servidor: %s", ex.getMessage());
        ErrorResponse genericResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", genericMessage, null, genericStatus.value());
        return ResponseEntity.status(genericStatus).body(genericResponse);
    }
}