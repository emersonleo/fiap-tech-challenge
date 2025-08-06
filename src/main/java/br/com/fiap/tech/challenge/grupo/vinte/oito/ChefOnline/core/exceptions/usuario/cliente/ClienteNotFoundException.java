package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ClienteNotFoundException extends CoreException {
    public static final String CODE = "CLIENTE_NOT_FOUND";

    private final Long id;
    private final String login;

    private ClienteNotFoundException(String message, Long id, String login) {
        super(CODE, message);
        this.id = id;
        this.login = login;
    }

    public static ClienteNotFoundException withId(Long id) {
        return new ClienteNotFoundException("Cliente não encontrado com o id: " + id, id, null);
    }

    public static ClienteNotFoundException withLogin(String login) {
        return new ClienteNotFoundException("Cliente não encontrado com o login: " + login, null, login);
    }

}
