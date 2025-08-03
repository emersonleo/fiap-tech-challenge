package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio;

public class ItemCardapioNotFoundException extends RuntimeException {

    public static final String CODE = "ITEM_CARDAPIO_NOT_FOUND";

    private final Long id;

    public ItemCardapioNotFoundException(String message) {
        super(message);
        this.id = null;
    }

    public ItemCardapioNotFoundException(Long id) {
        super("Item do cardápio não encontrado com o id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
