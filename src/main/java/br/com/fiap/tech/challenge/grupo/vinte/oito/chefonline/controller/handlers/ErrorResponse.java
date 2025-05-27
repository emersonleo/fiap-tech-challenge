package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller.handlers;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;
    private int statusCode;
    private String error;

    public ErrorResponse(String message, int statusCode, String error) {
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
    }

}
