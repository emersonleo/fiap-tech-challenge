package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record NovoItemCardapioDTO(

    Long id,

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    String descricao,

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin("0.0")
    BigDecimal preco,

    @NotNull(message = "A disponibilidade é obrigatória")
    Boolean disponibilidade,

    String foto,

    @NotNull(message = "o ID do restaurante é obrigatório")
    Long idRestaurante

) {

    public Object restaurante() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restaurante'");
    }
    
}