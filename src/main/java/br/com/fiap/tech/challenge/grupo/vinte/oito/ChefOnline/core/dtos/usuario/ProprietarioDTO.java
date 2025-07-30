package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import java.util.Date;

public record ProprietarioDTO(
        Long id,
        String nome,
        String email,
        String login,
        String senha,
        String endereco,
        Date dataUltimaAlteracao
) {
}
