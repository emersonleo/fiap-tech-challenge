package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio;

import java.math.BigDecimal;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean disponibilidade;
    private BigDecimal preco;
    private String foto;
    private Restaurante restaurante;

}
