package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entities;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dtos.ProprietarioRequestDTO;
import jakarta.persistence.Column;
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
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "razao_social", nullable = false, unique = true)
    private String razaoSocial;

    private String nomeFantasia;
    
    @Embedded
    private Usuario usuario;

    public Proprietario(ProprietarioRequestDTO proprietarioRequestDTO) {
        this.cnpj = proprietarioRequestDTO.cnpj();
        this.razaoSocial = proprietarioRequestDTO.razaoSocial();
        this.nomeFantasia = proprietarioRequestDTO.nomeFantasia();
        this.usuario = new Usuario();
        this.usuario.setNome(proprietarioRequestDTO.nome());
        this.usuario.setEmail(proprietarioRequestDTO.email());
        this.usuario.setLogin(proprietarioRequestDTO.login());
        this.usuario.setSenha(proprietarioRequestDTO.senha());
        this.usuario.setEndereco(proprietarioRequestDTO.endereco());
    }

}
