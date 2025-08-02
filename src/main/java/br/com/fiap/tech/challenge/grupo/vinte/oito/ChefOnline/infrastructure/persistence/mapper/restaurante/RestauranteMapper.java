package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.restaurante.RestauranteEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.ProprietarioMapper;

public class RestauranteMapper {

    public static Restaurante toDomain(RestauranteEntity restauranteEntity) {
        if (restauranteEntity == null) {
            return null;
        }

        return new Restaurante(
                restauranteEntity.getId(),
                restauranteEntity.getNomeRestaurante(),
                restauranteEntity.getEndereco(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioFuncionamento(),
                ProprietarioMapper.toDomain(restauranteEntity.getProprietario())
        );
    }

    public static RestauranteEntity toEntity(Restaurante novoRestauranteDomain) {
        if (novoRestauranteDomain == null) {
            return null;
        }

        return new RestauranteEntity(
                novoRestauranteDomain.getId(),
                novoRestauranteDomain.getNomeRestaurante(),
                novoRestauranteDomain.getEndereco(),
                novoRestauranteDomain.getTipoCozinha(),
                novoRestauranteDomain.getHorarioFuncionamento(),
                ProprietarioMapper.toEntity(novoRestauranteDomain.getProprietario())
        );
    }

    public static void updateEntityFromDomain(RestauranteEntity entity, Restaurante restaurante) {
        entity.setNomeRestaurante(restaurante.getNomeRestaurante());
        entity.setEndereco(restaurante.getEndereco());
        entity.setTipoCozinha(restaurante.getTipoCozinha());
        entity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        entity.setProprietario(ProprietarioMapper.toEntity(restaurante.getProprietario()));
    }

}