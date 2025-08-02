package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class EmailJaCadastrado extends CoreException {
    public static final String CODE = "EMAIL_DUPLICATE";

    private final String email;

    public EmailJaCadastrado(String email, NomeDoTipo tipo) {
        super(CODE, "Usuário do tipo " + tipo.name().toLowerCase() + " já cadastrado com o email: " + email);
        this.email = email;
    }

}
