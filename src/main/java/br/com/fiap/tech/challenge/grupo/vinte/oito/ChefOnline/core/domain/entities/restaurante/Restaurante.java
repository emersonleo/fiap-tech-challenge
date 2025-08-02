package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurante {

    private Long id;
    private String nomeRestaurante;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Proprietario proprietario;

}
