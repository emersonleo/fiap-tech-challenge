package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio;

import java.util.Optional;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;


public interface IItemCardapioGateway {
    ItemCardapio adicionaItemCardapio(ItemCardapio novoItem);

    Optional<ItemCardapio> buscaItemCardapioPorId(Long id);

    Optional<ItemCardapio> buscaTodosItensCardapio(int page, int size);

    Optional<ItemCardapio> buscaItensCardapioPorRestaurante(Long restauranteId, int page, int size);

    void atualizaItemCardapio(ItemCardapio itemCardapio);

    void deletaItemCardapio(ItemCardapio itemCardapio);
}