package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioRequestDTO;
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

    @Embedded
    private Usuario usuario;

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    public Proprietario(ProprietarioRequestDTO proprietarioRequestDTO) {
        this.cnpj = proprietarioRequestDTO.cnpj();
        this.razaoSocial = proprietarioRequestDTO.razaoSocial();
        this.nomeFantasia = proprietarioRequestDTO.nomeFantasia();
    }

}
