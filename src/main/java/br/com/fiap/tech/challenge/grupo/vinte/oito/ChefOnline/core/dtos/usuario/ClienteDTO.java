package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import java.util.Date;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String login,
        String endereco,
        Date dataUltimaAlteracao
) {
}