package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.cardapio.ItemCardapioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.restaurante.RestauranteMapper;

public class ItemCardapioMapper {
    public static ItemCardapio toDomain(ItemCardapioEntity itemCardapioEntity) {
        if (itemCardapioEntity == null) {
            return null;
        }

        var restauranteCardapio = RestauranteMapper.toDomain(itemCardapioEntity.getRestaurante());
        var restaurante = restauranteCardapio instanceof Restaurante r ? r : null;

        if (restaurante == null) {
            return null;
        }

        return new ItemCardapio(
                itemCardapioEntity.getId(),
                itemCardapioEntity.getNome(),
                itemCardapioEntity.getDescricao(),
                itemCardapioEntity.getDisponibilidadeConsumo(),
                itemCardapioEntity.getPreco(),
                itemCardapioEntity.getFoto(),
                restaurante
        );
    }

    public static ItemCardapioEntity toEntity(ItemCardapio itemCardapioDomain) {
        if (itemCardapioDomain == null) {
            return null;
        }

        return new ItemCardapioEntity(
                itemCardapioDomain.getId(),
                itemCardapioDomain.getNome(),
                itemCardapioDomain.getDescricao(),
                itemCardapioDomain.getPreco(),
                itemCardapioDomain.getFoto(),
                itemCardapioDomain.getDisponibilidadeConsumo(),
                RestauranteMapper.toEntity(itemCardapioDomain.getRestaurante())
        );
    }

    public static void updateEntityFromDomain(ItemCardapioEntity entity, ItemCardapio itemCardapio) {

        entity.setId(itemCardapio.getId());
        entity.setNome(itemCardapio.getNome());
        entity.setDescricao(itemCardapio.getDescricao());
        entity.setDisponibilidadeConsumo(itemCardapio.getDisponibilidadeConsumo());
        entity.setPreco(itemCardapio.getPreco());
        entity.setFoto(itemCardapio.getFoto());
        entity.setRestaurante(RestauranteMapper.toEntity(itemCardapio.getRestaurante()));
    }

}
