package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record AtualizaItemCardapioDTO(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        String descricao,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço deve ser positivo")
        BigDecimal preco,

        @NotNull(message = "A disponibilidade do consumo é obrigatória")
        List<DisponibilidadeConsumoPedidoEnum> disponibilidadeConsumo,

        String foto,

        @NotNull(message = "o ID do restaurante é obrigatório")
        Long idRestaurante
) {
}