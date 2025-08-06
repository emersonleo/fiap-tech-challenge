package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Restaurante> buscaTodosRestaurantes(int page, int size) {
        return restauranteDataSource.buscaTodosRestaurantes(page, size);
    }

    @Override
    public Optional<Restaurante> buscaRestaurantePorId(Long id) {
        return restauranteDataSource.buscaRestaurantePorId(id);
    }

    @Override
    public void deletaRestaurante(Restaurante restaurante) {
        restauranteDataSource.deletaRestaurante(restaurante);
    }

    @Override
    public void atualizaRestaurante(Restaurante restauranteExistente) {
        restauranteDataSource.atualizaRestaurante(restauranteExistente);
    }

    @Override
    public void deletaRestaurantePorProprietarioId(Long proprietarioId) {
        restauranteDataSource.deletaRestaurantePorProprietarioId(proprietarioId);
    }
}