package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;

import java.util.List;
import java.util.Optional;

public interface IRestauranteGateway {
    Restaurante adicionaRestaurante(Restaurante novoRestaurante);

    List<Restaurante> buscaTodosRestaurantes(int page, int size);

    Optional<Restaurante> buscaRestaurantePorId(Long id);

    void deletaRestaurante(Restaurante restaurante);

    void atualizaRestaurante(Restaurante restauranteExistente);
}