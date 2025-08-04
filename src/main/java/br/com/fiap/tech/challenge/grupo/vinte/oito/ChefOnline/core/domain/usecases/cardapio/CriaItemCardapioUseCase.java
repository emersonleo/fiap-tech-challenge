package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CriaItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;
    private final IRestauranteGateway restauranteGateway;

    public CriaItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static CriaItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        return new CriaItemCardapioUseCase(itemCardapioGateway, restauranteGateway);

    }

    public ItemCardapio run(NovoItemCardapioDTO novoItemCardapioDTO) {

        Optional<Restaurante> restaurante = restauranteGateway.buscaRestaurantePorId(novoItemCardapioDTO.idRestaurante());

        if (restaurante.isEmpty()) {
            throw new RestauranteNotFoundException(novoItemCardapioDTO.idRestaurante());
        }

        final ItemCardapio novoItemCardapio = new ItemCardapio(
                null,
                novoItemCardapioDTO.nome(),
                novoItemCardapioDTO.descricao(),
                converteDisponibilidadeConsumoParaString(novoItemCardapioDTO.disponibilidadeConsumo()),
                novoItemCardapioDTO.preco(),
                novoItemCardapioDTO.foto(),
                restaurante.get()
        );

        return itemCardapioGateway.adicionaItemCardapio(novoItemCardapio);
    }

    private String converteDisponibilidadeConsumoParaString(List<DisponibilidadeConsumoPedidoEnum> disponibilidadeConsumo) {
        return disponibilidadeConsumo.stream()
                .map(DisponibilidadeConsumoPedidoEnum::name)
                .collect(Collectors.joining(", "));
    }

}