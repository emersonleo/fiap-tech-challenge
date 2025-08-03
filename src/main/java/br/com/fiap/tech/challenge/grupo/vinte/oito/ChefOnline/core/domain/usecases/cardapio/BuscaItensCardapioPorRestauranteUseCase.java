package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import java.util.Optional;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class BuscaItensCardapioPorRestauranteUseCase {
        final IItemCardapioGateway itemCardapioGateway;

    public BuscaItensCardapioPorRestauranteUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static BuscaItensCardapioPorRestauranteUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new BuscaItensCardapioPorRestauranteUseCase(itemCardapioGateway);
    }

    public Optional<ItemCardapio> run(Long id) {
        return itemCardapioGateway.buscaItensCardapioPorRestaurante(id);
                
    }

}
