package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;

public record ItemCardapioDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    Boolean disponibilidade,
    String foto,
    RestauranteDTO restaurante
) {
}