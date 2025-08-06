package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.CoreException;
import lombok.Getter;

@Getter
public class ItemCardapioNotFoundException extends CoreException {

    public static final String CODE = "ITEM_CARDAPIO_NOT_FOUND";

    private final Long id;

    public ItemCardapioNotFoundException(String message) {
        super(CODE, message);
        this.id = null;
    }

    public ItemCardapioNotFoundException(Long id) {
        super(CODE, "Item Cardápio não encontrado com o id: " + id);
        this.id = id;
    }

}
