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
    private Date dataUltimaAlteracao;

    public Usuario() {
        this.dataUltimaAlteracao = new Date();
    }

    public Usuario(Long id, String nome, String email, String login, String senha, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAlteracao = new Date();
        this.endereco = endereco;
    }
}

