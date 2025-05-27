package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordDTO(

    @NotBlank(message = "O campo 'login' deve ser preenchido")
    String login,

    @NotBlank(message = "O campo 'Nova Senha' deve ser preenchido")
    String novaSenha,

    @NotBlank(message = "O campo 'CPF' deve ser preenchido")
    String CPF
) {
}
