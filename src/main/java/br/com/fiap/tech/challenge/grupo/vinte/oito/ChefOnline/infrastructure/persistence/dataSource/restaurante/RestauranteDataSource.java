package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.restaurante.RestauranteEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.restaurante.RestauranteMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.restaurante.RestauranteJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RestauranteDataSource implements IRestauranteDataSource {

    private final RestauranteJpaRepository repository;

    public RestauranteDataSource(RestauranteJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurante adicionaRestaurante(Restaurante novoRestaurante) {
        RestauranteEntity restauranteEntity = RestauranteMapper.toEntity(novoRestaurante);
        RestauranteEntity savedRestauranteEntity = repository.save(restauranteEntity);
        return RestauranteMapper.toDomain(savedRestauranteEntity);
    }

    @Override
    public List<Restaurante> buscaTodosRestaurantes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = repository.findAll(pageable);
        return pageResult.getContent()
                .stream()
                .map(RestauranteMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Restaurante> buscaRestaurantePorId(Long id) {
        Optional<RestauranteEntity> restauranteEntity = repository.findById(id);
        return restauranteEntity.map(RestauranteMapper::toDomain);
    }

    @Override
    public void deletaRestaurante(Restaurante restaurante) {
        if (restaurante.getId() != null) {
            repository.deleteById(restaurante.getId());
        }
    }

    @Override
    public void atualizaRestaurante(Restaurante restaurante) {
        Optional<RestauranteEntity> restauranteExistente = repository.findById(restaurante.getId());

        if(restauranteExistente.isPresent()){
            RestauranteEntity entity = restauranteExistente.get();
            RestauranteMapper.updateEntityFromDomain(entity, restaurante);
            repository.save(entity);
        }

    }

    @Override
    public void deletaRestaurantePorProprietarioId(Long proprietarioId) {
        repository.deleteByProprietarioId(proprietarioId);
    }
}