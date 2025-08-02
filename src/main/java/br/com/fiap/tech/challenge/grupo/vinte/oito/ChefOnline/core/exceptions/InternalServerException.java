package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions;

import lombok.Getter;

@Getter
public class InternalServerException extends CoreException {
    public static final String CODE = "INTERNAL_SERVER_ERROR";

    public InternalServerException(String message) {
        super(CODE, message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(CODE, message, cause);
    }
}