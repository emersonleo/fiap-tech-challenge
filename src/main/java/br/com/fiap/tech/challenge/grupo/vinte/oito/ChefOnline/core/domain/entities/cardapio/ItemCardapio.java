package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private String disponibilidadeConsumo;
    private BigDecimal preco;
    private String foto;
    private Restaurante restaurante;

}