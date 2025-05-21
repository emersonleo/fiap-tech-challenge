package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        String login,
        String senha,
        String endereco) {
}
