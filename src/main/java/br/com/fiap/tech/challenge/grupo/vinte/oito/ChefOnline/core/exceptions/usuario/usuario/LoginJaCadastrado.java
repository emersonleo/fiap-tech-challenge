package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class LoginJaCadastrado extends CoreException {
    public static final String CODE = "LOGIN_DUPLICATE";

    private final String login;

    public LoginJaCadastrado(String login, NomeDoTipo tipo) {
        super(CODE, "Usuário do tipo " + tipo.name().toLowerCase() + " já cadastrado com o login: " + login);
        this.login = login;
    }

}
