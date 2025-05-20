package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UsuarioDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Usuario {

    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private LocalDate dataUltimaAlteracaoRegistro;
    private LocalDate dataCriacaoRegistro;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.email = usuarioDTO.email();
        this.login = usuarioDTO.login();
        this.senha = usuarioDTO.senha();
        this.endereco = usuarioDTO.endereco();
        this.dataUltimaAlteracaoRegistro = usuarioDTO.dataUltimaAlteracaoRegistro();
        this.dataCriacaoRegistro = usuarioDTO.dataCriacaoRegistro();
    }

    public Usuario(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.nome();
        this.email = clienteDTO.email();
        this.login = clienteDTO.login();
        this.senha = clienteDTO.senha();
        this.endereco = clienteDTO.endereco();
    }

    public Usuario(ProprietarioDTO proprietarioDTO) {
        this.nome = proprietarioDTO.nome();
        this.email = proprietarioDTO.email();
        this.login = proprietarioDTO.login();
        this.senha = proprietarioDTO.senha();
        this.endereco = proprietarioDTO.endereco();
    }

}
