package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio.ItemCardapioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class AtualizaItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private AtualizaItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static AtualizaItemCardapioUseCase create(IItemCardapioGateway itemCardapio) {
        return new AtualizaItemCardapioUseCase(itemCardapio);
    }

    public void run(Long id, AtualizaItemCardapioDTO atualizaItemCardapioDTO) {
        final ItemCardapio itemCardapioExistente = itemCardapioGateway.buscaItemCardapioPorId(id)
            .orElseThrow(() -> new ItemCardapioNotFoundException(id));

        itemCardapioExistente.setNome(atualizaItemCardapioDTO.nome());
        itemCardapioExistente.setDescricao(atualizaItemCardapioDTO.descricao());
        itemCardapioExistente.setPreco(atualizaItemCardapioDTO.preco());
        itemCardapioExistente.setDisponibilidade(atualizaItemCardapioDTO.disponibilidade());
        itemCardapioExistente.setFoto(atualizaItemCardapioDTO.foto());
        itemCardapioExistente.getRestaurante().setId(atualizaItemCardapioDTO.idRestaurante());

        itemCardapioGateway.atualizaItemCardapio(itemCardapioExistente);
    }

}