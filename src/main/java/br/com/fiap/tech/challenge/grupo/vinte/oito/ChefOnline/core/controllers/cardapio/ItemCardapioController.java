package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.cardapio;

import java.util.List;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio.AtualizaItemCardapioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio.BuscaItemCardapioPorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio.BuscaItensCardapioPorRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio.CriaItemCardapioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio.DeletaItemCardapioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.ItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.cardapio.ItemCardapioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.cardapio.ItemCardapioPresenter;

public class ItemCardapioController {

    final IRestauranteDataSource restauranteDataSource;
    final IItemCardapioDataSource itemCardapioDataSource;

    public ItemCardapioController(IRestauranteDataSource restauranteDataSource, IItemCardapioDataSource itemCardapioDataSource) {
        this.restauranteDataSource = restauranteDataSource;
        this.itemCardapioDataSource = itemCardapioDataSource;
    }     
    
    public ItemCardapioDTO criaItemCardapio(NovoItemCardapioDTO novoItemCardapioDTO) {

        var itemCardapioGateway = ItemCardapioGateway.create(itemCardapioDataSource);
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);

        var criarItemCardapio = CriaItemCardapioUseCase.create(itemCardapioGateway, restauranteGateway);

        var itemCardapio = criarItemCardapio.run(novoItemCardapioDTO);
        return ItemCardapioPresenter.toDTO(itemCardapio);
    }

    public ItemCardapioDTO buscaItemCardapioPorId(Long id) {

        var itemCardapioGateway = ItemCardapioGateway.create(itemCardapioDataSource);
        var buscaItemCardapioPorId = BuscaItemCardapioPorIdUseCase.create(itemCardapioGateway);

        var itemCardapio = buscaItemCardapioPorId.run(id);

        return ItemCardapioPresenter.toDTO(itemCardapio);
        
    }

    public List<ItemCardapioDTO> buscaItensCardapioPorRestaurante(Long idRestaurante, int page, int size) {
        var itemCardapioGateway = ItemCardapioGateway.create(itemCardapioDataSource);

        var buscaItensCardapioPorRestaurante = BuscaItensCardapioPorRestauranteUseCase.create(itemCardapioGateway);

        return buscaItensCardapioPorRestaurante.run(idRestaurante, page, size)
                .stream()
                .map(ItemCardapioPresenter::toDTO)
                .toList();
    }

    public void atualizaItemCardapio(AtualizaItemCardapioDTO novoItemCardapioDTO) {
        var itemCardapioGateway = ItemCardapioGateway.create(itemCardapioDataSource);
        
        var atualizaItemCardapio = AtualizaItemCardapioUseCase.create(itemCardapioGateway);

        atualizaItemCardapio.run(novoItemCardapioDTO);
    }

    public void deletaItemCardapio(Long id) {
        var itemCardapioGateway = ItemCardapioGateway.create(itemCardapioDataSource);
        var deletaItemCardapio = DeletaItemCardapioUseCase.create(itemCardapioGateway);

        deletaItemCardapio.run(id);
    }

}