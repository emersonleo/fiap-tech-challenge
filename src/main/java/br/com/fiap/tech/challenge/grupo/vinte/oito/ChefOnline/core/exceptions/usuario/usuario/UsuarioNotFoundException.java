package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class UsuarioNotFoundException extends CoreException {
    public static final String CODE = "USUARIO_NOT_FOUND";

    private final Long id;

    public UsuarioNotFoundException(String message) {
        super(CODE, message);
        this.id = null;
    }

    public UsuarioNotFoundException(Long id) {
        super(CODE, "Usuario não encontrado com o id: " + id);
        this.id = id;
    }
}