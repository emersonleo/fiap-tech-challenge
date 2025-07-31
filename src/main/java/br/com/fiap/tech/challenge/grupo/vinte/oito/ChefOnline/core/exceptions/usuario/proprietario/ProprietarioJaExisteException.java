package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ProprietarioJaExisteException extends CoreException {
    public static final String CODE = "PROPRIETARIO_EXISTS";
    
    private final String email;

    public ProprietarioJaExisteException(String email) {
        super(CODE, "Proprietário já cadastrado com o email: " + email);
        this.email = email;
    }
}