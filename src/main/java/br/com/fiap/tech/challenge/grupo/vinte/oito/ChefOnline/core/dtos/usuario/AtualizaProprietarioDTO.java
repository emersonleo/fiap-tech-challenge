package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

public record AtualizaProprietarioDTO (
        String nome,
        String email,
        String login,
        String senha,
        String endereco
){
}