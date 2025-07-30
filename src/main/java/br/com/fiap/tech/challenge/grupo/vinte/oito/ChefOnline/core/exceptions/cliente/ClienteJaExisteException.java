package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ClienteJaExisteException extends CoreException {
    public static final String CODE = "CLIENTE_EXISTS";

    private final String email;

    public ClienteJaExisteException(String email) {
        super(CODE, "Cliente já cadastrado com o email: " + email);
        this.email = email;
    }

}
