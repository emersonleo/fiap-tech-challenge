package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.ItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;

public class CriaItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private CriaItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }       

    public static CriaItemCardapioUseCase create(IItemCardapioGateway itemCardapio) {
        return new CriaItemCardapioUseCase(itemCardapio);
    }

    public ItemCardapio run(NovoItemCardapioDTO novoItemCardapioDTO) {
        
        //TODO: melhorar a lógica da busca do item de cardápio
        // final ItemCardapio checkItemCardapio = itemCardapio.buscaItemCardapio(restauranteId); 
        // if (checkItemCardapio == null) {
        //     throw new CardapioJaExisteException(restauranteId);
        // }

        //TODO: melhorar a lógica de id do restaurante
        Long restauranteId = novoItemCardapioDTO.id() != null ? novoItemCardapioDTO.id() : 0L;
        final ItemCardapio novoItem = new ItemCardapio(
                novoItemCardapioDTO.id(),
                novoItemCardapioDTO.nome(),
                novoItemCardapioDTO.descricao(),
                novoItemCardapioDTO.preco(),
                novoItemCardapioDTO.disponibilidade(),
                novoItemCardapioDTO.foto()
        );

        return itemCardapioGateway.adicionaItemCardapio(novoItem, restauranteId);
    }       
}