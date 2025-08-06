package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio.BuscaItemCardapioPorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio.ItemCardapioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class BuscaItemCardapioPorIdUseCase {

    final IItemCardapioGateway itemCardapioGateway;

    public BuscaItemCardapioPorIdUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static BuscaItemCardapioPorIdUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new BuscaItemCardapioPorIdUseCase(itemCardapioGateway);
    }

    public ItemCardapio run(Long id) {
        return itemCardapioGateway.buscaItemCardapioPorId(id)
                .orElseThrow(() -> new ItemCardapioNotFoundException(id));
    }
}
