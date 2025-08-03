package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizaItemCardapioDTO(

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    String descricao,

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    Double preco,

    @NotNull(message = "A disponibilidade é obrigatória")
    Boolean disponibilidade,

    String foto, 
    
    @NotNull(message = "o ID do restaurante é obrigatório")
    Long idRestaurante
) {
}