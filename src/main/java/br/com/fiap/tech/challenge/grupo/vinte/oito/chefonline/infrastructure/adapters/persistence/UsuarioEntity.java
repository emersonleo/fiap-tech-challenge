package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    private String login;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String endereco;

    private LocalDateTime dataUltimaAlteracaoRegistro;
    private LocalDateTime dataCriacaoRegistro;
}
