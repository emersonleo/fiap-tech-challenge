package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.cliente;

public record ClienteResponse(
    String nome,
    String email,
    String login,
    String endereco,
    String cpf
) {}
