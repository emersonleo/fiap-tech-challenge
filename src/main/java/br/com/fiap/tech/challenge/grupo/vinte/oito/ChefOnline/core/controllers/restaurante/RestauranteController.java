package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.CriaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ClienteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.restaurante.RestaurantePresenter;

public class RestauranteController {
    private final IClienteDataSource clienteDataSource;
    private final IRestauranteDataSource restauranteDataSource;

    public RestauranteController(IClienteDataSource clienteDataSource, IRestauranteDataSource restauranteDataSource) {
        this.clienteDataSource = clienteDataSource;
        this.restauranteDataSource = restauranteDataSource;
    }

    public RestauranteDTO criaRestaurante(NovoRestauranteDTO novoRestauranteDTO) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);

        var criaRestaurante = CriaRestauranteUseCase.create(clienteGateway, restauranteGateway);

        var restaurante = criaRestaurante.run(novoRestauranteDTO);
//        return RestaurantePresenter.toDTO(restaurante);
        return null;
    }

}