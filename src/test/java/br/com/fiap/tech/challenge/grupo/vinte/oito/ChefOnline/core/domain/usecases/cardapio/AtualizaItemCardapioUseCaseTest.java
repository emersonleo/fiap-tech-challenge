package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio.ItemCardapioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AtualizaItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    private AtualizaItemCardapioUseCase atualizaItemCardapioUseCase;

    private ItemCardapio itemCardapio;

    private Restaurante restaurante;

    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        atualizaItemCardapioUseCase = AtualizaItemCardapioUseCase.create(itemCardapioGateway);

        proprietario = new Proprietario(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null
        );

        restaurante = new Restaurante(
                1L,
                "Pizzaria Delícia",
                "Rua das Flores, 123",
                "",
                "11:00 as 23:00",
                proprietario
        );

        itemCardapio = new ItemCardapio(
                null,
                "Pizza Margherita",
                "Deliciosa pizza com molho de tomate, mussarela e manjericão.",
                DisponibilidadeConsumoPedidoEnum.LOCAL.toString(),
                BigDecimal.valueOf(29.90),
                "https://example.com/pizza-margherita.jpg",
                restaurante
        );

    }


    @Test
    @DisplayName("Deve atualizar item do cardápio com sucesso")
    void deveAtualizarItemCardapioComSucesso() {
        // arrange
        Long itemId = 1L;
        AtualizaItemCardapioDTO dto = new AtualizaItemCardapioDTO(
                "Pizza Calabresa",
                "Pizza com muita calabresa e cebola",
                BigDecimal.valueOf(39.90),
                Arrays.asList(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-calabresa.jpg",
                restaurante.getId()
        );

        when(itemCardapioGateway.buscaItemCardapioPorId(itemId))
                .thenReturn(Optional.of(itemCardapio));

        // act
        atualizaItemCardapioUseCase.run(itemId, dto);

        // assert
        verify(itemCardapioGateway).buscaItemCardapioPorId(itemId);
        verify(itemCardapioGateway).atualizaItemCardapio(argThat(item ->
                item.getNome().equals(dto.nome()) &&
                        item.getDescricao().equals(dto.descricao()) &&
                        item.getPreco().equals(dto.preco()) &&
                        item.getFoto().equals(dto.foto()) &&
                        item.getRestaurante().getId().equals(dto.idRestaurante())
        ));
    }

    @Test
    @DisplayName("Deve lançar erro quando item não for encontrado")
    void deveLancarErroQuandoItemNaoForEncontrado() {
        // arrange
        Long itemId = 999L;
        AtualizaItemCardapioDTO dto = new AtualizaItemCardapioDTO(
                "Pizza Calabresa",
                "Pizza com muita calabresa e cebola",
                BigDecimal.valueOf(39.90),
                Arrays.asList(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-calabresa.jpg",
                restaurante.getId()
        );

        when(itemCardapioGateway.buscaItemCardapioPorId(itemId))
                .thenReturn(Optional.empty());

        // act & assert
        assertThrows(ItemCardapioNotFoundException.class, () ->
                atualizaItemCardapioUseCase.run(itemId, dto)
        );
        verify(itemCardapioGateway).buscaItemCardapioPorId(itemId);
        verify(itemCardapioGateway, never()).atualizaItemCardapio(any());
    }

}
