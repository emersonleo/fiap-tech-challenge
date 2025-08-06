package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import java.util.Date;

public record LoginResponseClienteDTO(
        String token,
        Date tokenExpiry
) {
}

