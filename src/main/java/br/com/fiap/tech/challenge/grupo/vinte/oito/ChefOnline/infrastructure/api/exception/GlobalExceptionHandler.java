package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.exception;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.auth.InvalidAuthException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.EmailJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.LoginJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.SenhaIncorretaException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for REST API responses.
 * <p>
 * HTTP Status Code Guidelines:
 * - 400 (Bad Request): Malformed requests, missing required fields, invalid JSON format
 * - 422 (Unprocessable Entity): Well-formed requests that fail business logic validation
 * - 404 (Not Found): Requested resource does not exist
 * - 409 (Conflict): Resource already exists or state conflict
 * - 500 (Internal Server Error): Unexpected server errors
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(responseCode = "400", description = "Requisição malformada - campos obrigatórios ausentes ou formato inválido")
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponse errorResponse = ErrorResponse.withDetails(
                "MALFORMED_REQUEST",
                "Dados de entrada inválidos",
                validationErrors,
                status
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ApiResponse(responseCode = "400", description = "Requisição malformada - JSON inválido ou corpo da requisição não pode ser lido")
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Formato de dados inválido ou JSON malformado";
        ErrorResponse errorResponse = new ErrorResponse("MALFORMED_JSON", message, null, status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ApiResponse(responseCode = "400", description = "Requisição malformada - variável de caminho obrigatória ausente")
    public ResponseEntity<ErrorResponse> handleMissingPathVariableException(MissingPathVariableException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = String.format("Variável de caminho obrigatória ausente: %s", ex.getVariableName());
        ErrorResponse errorResponse = new ErrorResponse("MISSING_PATH_VARIABLE", message, null, status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ApiResponse(responseCode = "400", description = "Requisição malformada - parâmetro obrigatório ausente")
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = String.format("Parâmetro obrigatório ausente: %s", ex.getParameterName());
        ErrorResponse errorResponse = new ErrorResponse("MISSING_REQUEST_PARAMETER", message, null, status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ApiResponse(responseCode = "400", description = "Erro de integridade de dados - violação de restrições de banco de dados")
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = String.format("Erro de integridade de dados: %s", ex.getMostSpecificCause().getMessage());
        ErrorResponse errorResponse = new ErrorResponse("DATA_INTEGRITY_VIOLATION", message, null, status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler({SenhaIncorretaException.class})
    @ApiResponse(responseCode = "403", description = "Permissão negada")
    public ResponseEntity<ErrorResponse> handlePermisisonDenied(CoreException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return ResponseEntity.status(status).body(ErrorResponse.fromCoreException(ex, status));
    }

    @ExceptionHandler({ClienteNotFoundException.class, ProprietarioNotFoundException.class, RestauranteNotFoundException.class})
    @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(CoreException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(ErrorResponse.fromCoreException(ex, status));
    }

    @ExceptionHandler({EmailJaCadastrado.class, LoginJaCadastrado.class})
    @ApiResponse(responseCode = "409", description = "Recurso já existe")
    public ResponseEntity<ErrorResponse> handleConflictExceptions(CoreException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(ErrorResponse.fromCoreException(ex, status));
    }

    @ExceptionHandler({InvalidAuthException.class})
    @ApiResponse(responseCode = "422", description = "Dados processáveis mas inválidos - falha nas regras de negócio ou autenticação")
    public ResponseEntity<ErrorResponse> handleInvalidExceptions(CoreException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status).body(ErrorResponse.fromCoreException(ex, status));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ApiResponse(responseCode = "400", description = "Erro de tipo de argumento - tipo de parâmetro incompatível com o esperado")
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String paramName = ex.getName();
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido";
        String message = String.format("O parâmetro '%s' deve ser do tipo '%s'", paramName, expectedType);
        ErrorResponse errorResponse = new ErrorResponse("TYPE_MISMATCH", message, null, status.value());
        return ResponseEntity.status(status).body(errorResponse);
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