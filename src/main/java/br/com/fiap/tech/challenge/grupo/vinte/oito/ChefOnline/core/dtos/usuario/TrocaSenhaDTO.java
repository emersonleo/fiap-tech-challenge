package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import jakarta.validation.constraints.NotBlank;

public record TrocaSenhaDTO(
        @NotBlank(message = "O login é obrigatório")
        String login,

        @NotBlank(message = "A senha atual é obrigatória")
        String senhaAtual,

        @NotBlank(message = "A nova senha é obrigatória")
        String novaSenha
) {
}