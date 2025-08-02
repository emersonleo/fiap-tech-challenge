package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ItemCardapio {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean disponibilidade;
    private BigDecimal preco;
    private String foto;

    public ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, Boolean disponibilidade, String foto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponibilidade = disponibilidade;
        this.foto = foto;
    }
}
