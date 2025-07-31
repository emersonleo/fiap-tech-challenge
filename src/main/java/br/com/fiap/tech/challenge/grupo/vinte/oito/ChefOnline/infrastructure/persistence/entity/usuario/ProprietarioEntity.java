package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("PROPRIETARIO")
@NoArgsConstructor
public class ProprietarioEntity extends UsuarioEntity {
    
    public ProprietarioEntity(Long id, String nome, String email, String login, String senha, String endereco, Date dataUltimaAlteracao) {
        super(id, nome, email, login, senha, endereco, dataUltimaAlteracao);
    }
}