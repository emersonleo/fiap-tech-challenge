package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteDTO;
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
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    public Cliente() {
        super();
    }

    public Cliente(ClienteDTO clienteDTO) {
        super(clienteDTO);
        this.cpf = clienteDTO.cpf();
    }
}
