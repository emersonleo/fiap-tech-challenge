package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.cardapio;

import java.util.Optional;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;

public class ItemCardapioGateway implements IItemCardapioGateway{
    private final IItemCardapioDataSource itemCardapioDataSource;

    private ItemCardapioGateway(IItemCardapioDataSource itemCardapioDataSource) {
        this.itemCardapioDataSource = itemCardapioDataSource;
    }

    public static ItemCardapioGateway create(IItemCardapioDataSource itemCardapioDataSource) {
        return new ItemCardapioGateway(itemCardapioDataSource);
    }

    public ItemCardapio adicionaItemCardapio(ItemCardapio novoItemCardapio) {
        return itemCardapioDataSource.adicionaItemCardapio(novoItemCardapio);
    }

    public Optional<ItemCardapio> buscaItemCardapioPorId(Long id) {
        return itemCardapioDataSource.buscaItemCardapioPorId(id);
    }

    public Optional<ItemCardapio> buscaTodosItensCardapio(int page, int size) {
        return itemCardapioDataSource.buscaTodosItensCardapio(page, size);
    }

    public Optional<ItemCardapio> buscaItensCardapioPorRestaurante(Long restauranteId) {
        return itemCardapioDataSource.buscaItensCardapioPorRestaurante(restauranteId);
    }

    public void atualizaItemCardapio(ItemCardapio itemCardapioExistente) {
        itemCardapioDataSource.atualizaItemCardapio(itemCardapioExistente);
    }

    public void deletaItemCardapio(ItemCardapio itemCardapio) {
        itemCardapioDataSource.deletaItemCardapio(itemCardapio);
    }


}
