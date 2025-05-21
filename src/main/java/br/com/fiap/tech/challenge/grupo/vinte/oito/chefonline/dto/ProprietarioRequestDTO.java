package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

public record ProprietarioRequestDTO(
        String nome,
        String email,
        String login,
        String senha,
        String endereco,
        String cnpj,
        String razaoSocial,
        String nomeFantasia) {
}
