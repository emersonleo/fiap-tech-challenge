package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaItemCardapioDTO(

    @NotNull(message = "O ID é obrigatório")
    Long id,

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    String descricao,

    @NotBlank(message = "O preço é obrigatório")
    @DecimalMin("0.0")
    BigDecimal preco,

    @NotNull(message = "A disponibilidade é obrigatória")
    Boolean disponibilidade,

    String foto, 
    
    @NotNull(message = "o ID do restaurante é obrigatório")
    Long idRestaurante
) {
}