package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends Usuario {

    public Cliente() {
        super(NomeDoTipo.CLIENTE);
    }

    public Cliente(Long id, String nome, String email, String login, String senha, String endereco) {
        super(id, nome, email, login, senha, endereco, NomeDoTipo.CLIENTE);
    }
}
