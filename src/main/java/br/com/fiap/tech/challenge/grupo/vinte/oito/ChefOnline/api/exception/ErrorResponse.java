package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api.exception;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
    String code, 
    String message, 
    Map<String, String> details,
    int status
) {
    public static ErrorResponse fromCoreException(CoreException exception, HttpStatus status) {
        return new ErrorResponse(exception.getCode(), exception.getMessage(), null, status.value());
    }
    
    public static ErrorResponse withDetails(String code, String message, Map<String, String> details, HttpStatus status) {
        return new ErrorResponse(code, message, details, status.value());
    }
}