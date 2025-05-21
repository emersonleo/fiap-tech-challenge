package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UsuarioDTO;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Usuario {

    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private LocalDate dataUltimaAlteracaoRegistro;
    private LocalDate dataCriacaoRegistro;

}
