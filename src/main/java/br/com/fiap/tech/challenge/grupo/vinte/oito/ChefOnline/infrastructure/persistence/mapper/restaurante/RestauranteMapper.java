package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.restaurante.RestauranteEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.UsuarioMapper;

public class RestauranteMapper {

    public static Restaurante toDomain(RestauranteEntity restauranteEntity) {
        if (restauranteEntity == null) {
            return null;
        }

        var proprietarioUsuario = UsuarioMapper.toDomain(restauranteEntity.getProprietario());
        var proprietario = proprietarioUsuario instanceof Proprietario p ? p : null;

        if (proprietario == null) {
            return null;
        }

        return new Restaurante(
                restauranteEntity.getId(),
                restauranteEntity.getNomeRestaurante(),
                restauranteEntity.getEndereco(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioFuncionamento(),
                proprietario
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
                UsuarioMapper.toEntity(novoRestauranteDomain.getProprietario())
        );
    }

    public static void updateEntityFromDomain(RestauranteEntity entity, Restaurante restaurante) {
        entity.setNomeRestaurante(restaurante.getNomeRestaurante());
        entity.setEndereco(restaurante.getEndereco());
        entity.setTipoCozinha(restaurante.getTipoCozinha());
        entity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        entity.setProprietario(UsuarioMapper.toEntity(restaurante.getProprietario()));
    }

}