package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

public class DeletaRestauranteUseCase {
    final IRestauranteGateway restauranteGateway;

    public DeletaRestauranteUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static DeletaRestauranteUseCase create(IRestauranteGateway restauranteGateway) {
        return new DeletaRestauranteUseCase(restauranteGateway);
    }

    public void run(Long id) {
        final Restaurante restaurante = restauranteGateway.buscaRestaurantePorId(id)
                .orElseThrow(() -> new RestauranteNotFoundException(id));

        restauranteGateway.deletaRestaurante(restaurante);
    }
}
