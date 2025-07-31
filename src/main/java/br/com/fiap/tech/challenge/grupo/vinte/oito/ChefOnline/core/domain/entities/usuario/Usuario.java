package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private final NomeDoTipo tipo;
    private Date dataUltimaAlteracao;

    public Usuario(NomeDoTipo tipo) {
        this.dataUltimaAlteracao = new Date();
        this.tipo = tipo;
    }

    public Usuario(Long id, String nome, String email, String login, String senha, String endereco, NomeDoTipo tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.endereco = endereco;
        this.dataUltimaAlteracao = new Date();
    }
}

