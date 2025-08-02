package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.CriaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.restaurante.RestaurantePresenter;

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
        return null;
    }
}