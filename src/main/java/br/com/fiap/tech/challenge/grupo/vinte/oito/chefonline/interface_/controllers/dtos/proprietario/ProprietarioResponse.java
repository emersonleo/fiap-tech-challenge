package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.proprietario;

public record ProprietarioResponse(
    String nome,
    String email,
    String login,
    String endereco,
    String cnpj
) {}
