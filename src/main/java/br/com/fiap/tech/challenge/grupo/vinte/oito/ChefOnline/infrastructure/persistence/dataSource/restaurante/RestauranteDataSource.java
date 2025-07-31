package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.restaurante.RestauranteJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDataSource implements IRestauranteDataSource {

    private final RestauranteJpaRepository repository;

    public RestauranteDataSource(RestauranteJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Restaurante adicionaRestaurante(Restaurante novoRestaurante) {
        return null;
    }
}