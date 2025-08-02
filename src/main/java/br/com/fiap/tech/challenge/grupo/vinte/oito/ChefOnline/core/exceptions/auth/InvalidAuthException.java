package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.auth;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class InvalidAuthException extends CoreException {
    public static final String CODE = "INVALID_AUTH";

    public InvalidAuthException(NomeDoTipo tipoUsuario) {
        super(CODE, String.format("Credenciais inválidas ou usuário do tipo %s não existente para as credenciais informadas", tipoUsuario.name().toLowerCase()));
    }
}
