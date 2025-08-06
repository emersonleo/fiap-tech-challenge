package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Cliente extends Usuario {
    public Cliente(Long id, String nome, String email, String login, String senha, String endereco) {
        super(NomeDoTipo.CLIENTE, id, nome, email, login, senha, endereco, null);
    }

    public Cliente(Long id, String nome, String email, String login, String senha, String endereco, Date dataUltimaAlteracao) {
        super(NomeDoTipo.CLIENTE, id, nome, email, login, senha, endereco, dataUltimaAlteracao);
    }
}
