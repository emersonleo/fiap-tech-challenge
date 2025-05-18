package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

import java.time.LocalDate;

public record UsuarioDTO(
        String nome,
        String email,
        String login,
        String senha,
        String endereco,
        LocalDate dataUltimaAlteracaoRegistro,
        LocalDate dataCriacaoRegistro) {
}
