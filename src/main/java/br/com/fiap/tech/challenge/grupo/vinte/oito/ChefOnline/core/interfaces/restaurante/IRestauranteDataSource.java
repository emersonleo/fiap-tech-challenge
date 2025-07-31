package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;

public interface IRestauranteDataSource {
    Restaurante adicionaRestaurante(Restaurante novoRestaurante);
}