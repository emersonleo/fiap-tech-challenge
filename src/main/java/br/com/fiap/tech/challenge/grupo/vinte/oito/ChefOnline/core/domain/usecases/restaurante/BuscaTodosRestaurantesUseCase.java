package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

import java.util.List;

public class BuscaTodosRestaurantesUseCase {
    final IRestauranteGateway restauranteGateway;

    public BuscaTodosRestaurantesUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static BuscaTodosRestaurantesUseCase create(IRestauranteGateway restauranteGateway) {
        return new BuscaTodosRestaurantesUseCase(restauranteGateway);
    }

    public List<Restaurante> run(int page, int size) {
        return restauranteGateway.buscaTodosRestaurantes(page, size);
    }
}
