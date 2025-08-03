package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class AtualizarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private AtualizarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static AtualizarItemCardapioUseCase create(IItemCardapioGateway itemCardapio) {
        return new AtualizarItemCardapioUseCase(itemCardapio);
    }

    public void run(AtualizaItemCardapioDTO atualizaItemCardapioDTO, Long id) {
        final ItemCardapio itemCardapioExistente = itemCardapioGateway.buscaItemCardapioPorId(id)
            .orElseThrow(() -> new RestauranteNotFoundException(id));

        itemCardapioExistente.setNome(atualizaItemCardapioDTO.nome());
        itemCardapioExistente.setDescricao(atualizaItemCardapioDTO.descricao());
        itemCardapioExistente.setPreco(atualizaItemCardapioDTO.preco());
        itemCardapioExistente.setDisponibilidade(atualizaItemCardapioDTO.disponibilidade());
        itemCardapioExistente.setFoto(atualizaItemCardapioDTO.foto());
        itemCardapioExistente.getRestaurante().setId(atualizaItemCardapioDTO.idRestaurante());

        itemCardapioGateway.atualizaItemCardapio(itemCardapioExistente);
    }

}