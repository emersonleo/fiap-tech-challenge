package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.AtualizaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.BuscaRestaurantePorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.BuscaTodosRestaurantesUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.CriaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.DeletaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.restaurante.RestaurantePresenter;

import java.util.List;

public class RestauranteController {
    private final IProprietarioDataSource proprietarioDataSource;
    private final IRestauranteDataSource restauranteDataSource;

    public RestauranteController(IProprietarioDataSource proprietarioDataSource, IRestauranteDataSource restauranteDataSource) {
        this.proprietarioDataSource = proprietarioDataSource;
        this.restauranteDataSource = restauranteDataSource;
    }

    public RestauranteDTO criaRestaurante(NovoRestauranteDTO novoRestauranteDTO) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);

        var criaRestaurante = CriaRestauranteUseCase.create(proprietarioGateway, restauranteGateway);

        var restaurante = criaRestaurante.run(novoRestauranteDTO);
        return RestaurantePresenter.toDTO(restaurante);
    }

    public RestauranteDTO buscaRestaurantePorId(Long id) {
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        var buscaRestaurantePorId = BuscaRestaurantePorIdUseCase.create(restauranteGateway);

        var restaurante = buscaRestaurantePorId.run(id);
        return RestaurantePresenter.toDTO(restaurante);
    }

    public List<RestauranteDTO> buscaTodosRestaurantes(int page, int size) {
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        var buscaTodosRestaurantes = BuscaTodosRestaurantesUseCase.create(restauranteGateway);

        return buscaTodosRestaurantes.run(page, size)
                .stream()
                .map(RestaurantePresenter::toDTO)
                .toList();
    }

    public void atualizaRestaurante(AtualizaRestauranteDTO atualizaRestauranteDTO, Long id) {
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        var atualizaRestaurante = AtualizaRestauranteUseCase.create(restauranteGateway);

        atualizaRestaurante.run(atualizaRestauranteDTO, id);
    }

    public void deletaRestaurante(Long id) {
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        var deletaRestaurante = DeletaRestauranteUseCase.create(restauranteGateway);

        deletaRestaurante.run(id);
    }

}