package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaRestauranteDTO(
        @NotBlank(message = "o nome do restaurante é obrigatório")
        String nomeRestaurante,

        @NotBlank(message = "o endereço do restaurante é obrigatório")
        String endereco,

        String tipoCozinha,

        @NotBlank(message = "o horário de funcionamento do restaurante é obrigatório")
        String horarioFuncionamento,

        @NotNull(message = "o ID do proprietario do restaurante é obrigatório")
        Long idProprietario
) {
}