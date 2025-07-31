package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ProprietarioNotFoundException extends CoreException {
    public static final String CODE = "PROPRIETARIO_NOT_FOUND";

    private final Long id;

    public ProprietarioNotFoundException(String message) {
        super(CODE, message);
        this.id = null;
    }

    public ProprietarioNotFoundException(Long id) {
        super(CODE, "Proprietário não encontrado com o id: " + id);
        this.id = id;
    }
}