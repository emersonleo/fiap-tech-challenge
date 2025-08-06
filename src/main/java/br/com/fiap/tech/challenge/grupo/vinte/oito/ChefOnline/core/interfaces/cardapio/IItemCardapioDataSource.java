package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio;

import java.util.List;
import java.util.Optional;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;

public interface IItemCardapioDataSource {

    ItemCardapio adicionaItemCardapio(ItemCardapio novoItem);

    Optional<ItemCardapio> buscaItemCardapioPorId(Long id);

    List<ItemCardapio> buscaTodosItensCardapio(int page, int size);

    List<ItemCardapio> buscaItensCardapioPorRestaurante(Long restauranteId);

    void atualizaItemCardapio(ItemCardapio itemCardapio);
    
    void deletaItemCardapio(ItemCardapio itemCardapio);

}