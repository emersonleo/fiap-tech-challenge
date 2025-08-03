package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public abstract class Usuario {
    private final NomeDoTipo tipo;
    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private final Date dataUltimaAlteracao;

    public Usuario convertByTipo(NomeDoTipo targetTipo) {
        if (targetTipo == null) {
            throw new IllegalArgumentException("Tipo de destino não pode ser nulo");
        }

        if (this.tipo == targetTipo) {
            return this;
        }

        return switch (targetTipo) {
            case CLIENTE -> new Cliente(this.id, this.nome, this.email, this.login,
                    this.senha, this.endereco, this.dataUltimaAlteracao);
            case PROPRIETARIO -> new Proprietario(this.id, this.nome, this.email, this.login,
                    this.senha, this.endereco, this.dataUltimaAlteracao);
        };
    }
}
