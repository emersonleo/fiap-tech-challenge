package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente;

import jakarta.validation.constraints.NotBlank;

public record VerificaCredenciaisDTO(
        @NotBlank(message = "O login é obrigatório")
        String login,

        @NotBlank(message = "A senha é obrigatória")
        String senha
) {
}

