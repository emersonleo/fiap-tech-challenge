package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio.ItemCardapioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class DeletaItemCardapioUseCase {
        final IItemCardapioGateway itemCardapioGateway;

    public DeletaItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static DeletaItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new DeletaItemCardapioUseCase(itemCardapioGateway);
    }

    public void run(Long id) {
        final ItemCardapio itemCardapio = itemCardapioGateway.buscaItemCardapioPorId(id)
                .orElseThrow(() -> new ItemCardapioNotFoundException(id));

        itemCardapioGateway.deletaItemCardapio(itemCardapio);
    }
}
