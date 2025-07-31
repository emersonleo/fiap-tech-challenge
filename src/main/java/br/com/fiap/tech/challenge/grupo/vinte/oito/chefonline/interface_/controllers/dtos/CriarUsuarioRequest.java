package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos;

public record CriarUsuarioRequest(
    String nome,
    String email,
    String login,
    String senha,
    String endereco
) {}
