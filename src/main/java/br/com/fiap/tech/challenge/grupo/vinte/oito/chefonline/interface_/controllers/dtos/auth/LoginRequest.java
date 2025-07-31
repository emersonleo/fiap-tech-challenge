package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "O campo 'login' deve ser preenchido")
    String login,

    @NotBlank(message = "O campo 'senha' deve ser preenchido")
    String senha
) {}
