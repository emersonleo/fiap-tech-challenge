package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Proprietario extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    public Proprietario(){
        super();
    }

    public Proprietario(ProprietarioDTO proprietarioDTO) {
        super(proprietarioDTO);
        this.cnpj = proprietarioDTO.cnpj();
        this.razaoSocial = proprietarioDTO.razaoSocial();
        this.nomeFantasia = proprietarioDTO.nomeFantasia();
    }

}
