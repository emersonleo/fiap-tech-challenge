package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

public record ClienteDTO(
        String nome,
        String cpf,
        String email,
        String login,
        String senha,
        String endereco) {
}
