package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;

public class SenhaIncorretaException extends CoreException {
    private static final String CODE = "SENHA_INCORRETA";
    public SenhaIncorretaException(String message) {
        super(CODE, message);
    }
}