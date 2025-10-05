package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.DTO;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}
