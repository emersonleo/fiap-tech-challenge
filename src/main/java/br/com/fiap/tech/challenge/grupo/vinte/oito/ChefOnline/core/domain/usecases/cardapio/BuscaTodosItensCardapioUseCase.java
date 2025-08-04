package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import java.util.List;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class BuscaTodosItensCardapioUseCase {
        final IItemCardapioGateway itemCardapioGateway;

    public BuscaTodosItensCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static BuscaTodosItensCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new BuscaTodosItensCardapioUseCase(itemCardapioGateway);
    }

    public List<ItemCardapio> run(int page, int size) {
        return itemCardapioGateway.buscaTodosItensCardapio(page, size);
                
    }

}
