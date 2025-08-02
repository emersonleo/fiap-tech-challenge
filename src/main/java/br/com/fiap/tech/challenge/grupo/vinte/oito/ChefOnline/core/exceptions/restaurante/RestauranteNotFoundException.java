package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;

public class RestauranteNotFoundException extends CoreException {

    public static final String CODE = "RESTAURANTE_NOT_FOUND";

    private final Long id;

    public RestauranteNotFoundException(String message) {
        super(CODE, message);
        this.id = null;
    }

    public RestauranteNotFoundException(Long id) {
        super(CODE, "Restaurante não encontrado com o id: " + id);
        this.id = id;
    }
}
