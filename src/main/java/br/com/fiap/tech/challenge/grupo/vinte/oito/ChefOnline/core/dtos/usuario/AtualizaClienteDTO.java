package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

public record AtualizaClienteDTO (
        String nome,
        String email,
        String login,
        String senha,
        String endereco
){
}
