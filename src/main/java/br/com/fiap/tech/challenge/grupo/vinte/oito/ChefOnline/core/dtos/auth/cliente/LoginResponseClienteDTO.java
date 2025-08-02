package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record LoginResponseClienteDTO(
        String token,
        Date tokenExpiry
) {
}

