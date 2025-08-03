package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import java.util.Date;

public record LoginResponseProprietarioDTO(
        String token,
        Date tokenExpiry
) {
}
