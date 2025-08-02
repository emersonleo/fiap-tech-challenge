package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio;

import java.util.List;
import java.util.Optional;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;


public interface IItemCardapioGateway {
    ItemCardapio adicionaItemCardapio(ItemCardapio novoItem, Long restauranteId);
    Optional<ItemCardapio> buscaItemCardapioPorId(Long id);
    List<ItemCardapio> buscaTodosItemCardapios(int page, int size);
    List<ItemCardapio> buscaTodosItemCardapiosPorRestaurante(int page, int size, Long restauranteId);
    ItemCardapio atualizaItemCardapio(ItemCardapio itemCardapio);
    void deletaItemCardapio(ItemCardapio itemCardapio);
}