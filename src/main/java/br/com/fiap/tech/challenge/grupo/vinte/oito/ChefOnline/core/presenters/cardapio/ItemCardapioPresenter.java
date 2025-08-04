package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.ItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.restaurante.RestaurantePresenter;

import java.util.Arrays;
import java.util.List;

public class ItemCardapioPresenter {

    public static ItemCardapioDTO toDTO(ItemCardapio itemCardapio) {
        String disponibilidadesString = itemCardapio.getDisponibilidadeConsumo();
        String[] disponibilidadesArray = disponibilidadesString.split(", ");

        List<DisponibilidadeConsumoPedidoEnum> disponibilidadeConsumoPedidoEnumList =
                Arrays.stream(disponibilidadesArray)
                        .map(DisponibilidadeConsumoPedidoEnum::valueOf)
                        .toList();

        return new ItemCardapioDTO(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                disponibilidadeConsumoPedidoEnumList,
                itemCardapio.getFoto(),
                restauranteDTO(itemCardapio.getRestaurante())
        );
    }

    private static RestauranteDTO restauranteDTO(Restaurante restaurante) {
        return RestaurantePresenter.toDTO(restaurante);
    }

}