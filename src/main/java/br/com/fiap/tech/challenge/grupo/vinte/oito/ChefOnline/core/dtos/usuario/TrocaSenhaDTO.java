package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

public record TrocaSenhaDTO(
        String login,
        String senhaAtual,
        String novaSenha
) {
}