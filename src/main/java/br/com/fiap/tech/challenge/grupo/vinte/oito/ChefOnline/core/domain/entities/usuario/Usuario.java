package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
    private final NomeDoTipo tipo;
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private Date dataUltimaAlteracao;
}

