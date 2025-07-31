package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

public class RestauranteGateway implements IRestauranteGateway {
    private final IRestauranteDataSource restauranteDataSource;

    private RestauranteGateway(IRestauranteDataSource restauranteDataSource) {
        this.restauranteDataSource = restauranteDataSource;
    }

    public static RestauranteGateway create(IRestauranteDataSource restauranteDataSource) {
        return new RestauranteGateway(restauranteDataSource);
    }


    @Override
    public Restaurante adicionaRestaurante(Restaurante novoRestaurante) {
        return restauranteDataSource.adicionaRestaurante(novoRestaurante);
    }
}