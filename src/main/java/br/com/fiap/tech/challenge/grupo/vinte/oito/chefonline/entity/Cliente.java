package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteRequestDTO;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @Embedded
    private Usuario usuario;

    public Cliente(ClienteRequestDTO clienteRequestDTO) {
        this.cpf = clienteRequestDTO.cpf();
        this.usuario = new Usuario();
        this.usuario.setNome(clienteRequestDTO.nome());
        this.usuario.setEmail(clienteRequestDTO.email());
        this.usuario.setLogin(clienteRequestDTO.login());
        this.usuario.setSenha(clienteRequestDTO.senha());
        this.usuario.setEndereco(clienteRequestDTO.endereco());
    }

}
