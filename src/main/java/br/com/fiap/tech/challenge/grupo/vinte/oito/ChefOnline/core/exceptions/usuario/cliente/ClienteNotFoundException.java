package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ClienteNotFoundException extends CoreException {
    public static final String CODE = "CLIENTE_NOT_FOUND";

    private final Long id;

    public ClienteNotFoundException(String message) {
        super(CODE, message);
        this.id = null;
    }

    public ClienteNotFoundException(Long id) {
        super(CODE, "Cliente não encontrado com o id: " + id);
        this.id = id;
    }

}
