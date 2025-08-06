package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

public class BuscaRestaurantePorIdUseCase {
    final IRestauranteGateway restauranteGateway;

    public BuscaRestaurantePorIdUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static BuscaRestaurantePorIdUseCase create(IRestauranteGateway restauranteGateway) {
        return new BuscaRestaurantePorIdUseCase(restauranteGateway);
    }

    public Restaurante run(Long id) {
        return restauranteGateway.buscaRestaurantePorId(id)
                .orElseThrow(() -> new RestauranteNotFoundException(id));
    }
}
