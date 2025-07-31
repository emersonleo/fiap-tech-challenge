package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(
    @NotBlank(message = "A senha atual deve ser informada")
    String senhaAtual,

    @NotBlank(message = "A nova senha deve ser informada")
    String novaSenha
) {}
