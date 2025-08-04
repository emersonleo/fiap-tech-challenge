package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;

import java.util.List;

public record ItemCardapioDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    List<DisponibilidadeConsumoPedidoEnum> disponibilidadeConsumo,
    String foto,
    RestauranteDTO restaurante
) {
}