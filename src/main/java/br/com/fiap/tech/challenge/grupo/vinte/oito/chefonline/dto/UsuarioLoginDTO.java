package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(

        @NotBlank(message = "O campo 'login' deve ser preenchido")
        String login,

        @NotBlank(message = "O campo 'senha' deve ser preenchido")
        String senha) {
}
