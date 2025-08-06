package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriaItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    @Mock
    private IRestauranteGateway restauranteGateway;

    private CriaItemCardapioUseCase criaItemCardapioUseCase;

    private ItemCardapio itemCardapio;

    private Restaurante restaurante;

    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criaItemCardapioUseCase = CriaItemCardapioUseCase.create(itemCardapioGateway, restauranteGateway);

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
    @DisplayName("Deve adicionar item ao cardápio com sucesso")
    void deveAdicionarItemAoCardapioComSucesso() {
        // arrange
        NovoItemCardapioDTO dto = new NovoItemCardapioDTO(
                null,
                "Pizza Margherita",
                "Deliciosa pizza com molho de tomate, mussarela e manjericão.",
                BigDecimal.valueOf(29.90),
                List.of(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-margherita.jpg",
                restaurante.getId()
        );

        when(restauranteGateway.buscaRestaurantePorId(restaurante.getId())).thenReturn(Optional.of(restaurante));
        when(itemCardapioGateway.adicionaItemCardapio(any())).thenReturn(itemCardapio);

        // act
        ItemCardapio resultado = criaItemCardapioUseCase.run(dto);

        // assert
        assertNotNull(resultado);
        assertEquals(itemCardapio.getNome(), resultado.getNome());
        verify(itemCardapioGateway).adicionaItemCardapio(any());
        verify(restauranteGateway).buscaRestaurantePorId(restaurante.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não encontrado")
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        // arrange
        NovoItemCardapioDTO dto = new NovoItemCardapioDTO(
                null,
                "Pizza Margherita",
                "Deliciosa pizza com molho de tomate, mussarela e manjericão.",
                BigDecimal.valueOf(29.90),
                List.of(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-margherita.jpg",
                999L // ID de restaurante inexistente
        );

        when(restauranteGateway.buscaRestaurantePorId(dto.idRestaurante())).thenReturn(Optional.empty());

        // act & assert
        assertThrows(RestauranteNotFoundException.class, () -> criaItemCardapioUseCase.run(dto));
        verify(restauranteGateway).buscaRestaurantePorId(dto.idRestaurante());
        verify(itemCardapioGateway, never()).adicionaItemCardapio(any());
    }

}
