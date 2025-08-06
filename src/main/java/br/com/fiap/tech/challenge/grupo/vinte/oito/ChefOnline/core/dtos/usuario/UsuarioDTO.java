package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;

import java.util.Date;

public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        String login,
        String endereco,
        NomeDoTipo tipo,
        Date dataUltimaAlteracao
) {
}
