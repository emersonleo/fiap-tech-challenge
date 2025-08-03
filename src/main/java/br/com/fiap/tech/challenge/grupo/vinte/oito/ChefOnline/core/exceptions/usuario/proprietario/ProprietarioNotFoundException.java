package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ProprietarioNotFoundException extends CoreException {
    public static final String CODE = "PROPRIETARIO_NOT_FOUND";

    private final Long id;
    private final String login;

    private ProprietarioNotFoundException(String message, Long id, String login) {
        super(CODE, message);
        this.id = id;
        this.login = login;
    }

    public static ProprietarioNotFoundException withId(Long id) {
        return new ProprietarioNotFoundException("Proprietário não encontrado com o id: " + id, id, null);
    }

    public static ProprietarioNotFoundException withLogin(String login) {
        return new ProprietarioNotFoundException("Proprietário não encontrado com o login: " + login, null, login);
    }

}