package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.cardapio.CardapioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.restaurante.RestauranteMapper;

public class ItemCardapioMapper {
    public static ItemCardapio toDomain(CardapioEntity itemCardapioEntity) {
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
                itemCardapioEntity.getDisponiblilidade(),
                itemCardapioEntity.getPreco(),
                itemCardapioEntity.getFoto(),
                restaurante
        );
    }

    public static CardapioEntity toEntity(ItemCardapio itemCardapioDomain) {
        if (itemCardapioDomain == null) {
            return null;
        }

        return new CardapioEntity(
                itemCardapioDomain.getId(),
                itemCardapioDomain.getNome(),
                itemCardapioDomain.getDescricao(),
                itemCardapioDomain.getPreco(),
                itemCardapioDomain.getFoto(),
                itemCardapioDomain.getDisponibilidade(),
                RestauranteMapper.toEntity(itemCardapioDomain.getRestaurante())
        );
    }

}
