package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante;

import jakarta.validation.constraints.NotBlank;

public record NovoRestauranteDTO(
        @NotBlank(message = "o nome do restaurante é obrigatório")
        String nomeRestaurante,

        @NotBlank(message = "o endereço do restaurante é obrigatório")
        String endereco,

        String tipoCozinha,

        String horarioFuncionamento,

        @NotBlank(message = "o ID do dono do restaurante é obrigatório")
        Long idDonoRestaurante
) {
}