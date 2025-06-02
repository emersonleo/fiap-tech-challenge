package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Usuario {

    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "login", nullable = false, unique = true)  
    private String login;
    private String senha;

    @Column(name = "endereco", nullable = false)
    private String endereco;
    private LocalDateTime dataUltimaAlteracaoRegistro;
    private LocalDateTime dataCriacaoRegistro;

}
