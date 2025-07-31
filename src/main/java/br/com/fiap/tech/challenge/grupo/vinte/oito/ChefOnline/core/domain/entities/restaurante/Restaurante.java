package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurante {

    private String nomeRestaurante;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Long idDonoRestaurante;

}
