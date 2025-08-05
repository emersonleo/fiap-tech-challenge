package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.ItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemCardapioControllerTest {

    @Mock
    private IRestauranteDataSource restauranteDataSource;

    @Mock
    private IItemCardapioDataSource itemCardapioDataSource;

    private ItemCardapioController controller;

    private Proprietario proprietario;

    private Restaurante restaurante;

    private ItemCardapio itemCardapio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ItemCardapioController(restauranteDataSource, itemCardapioDataSource);

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
    void deveCriarItemCardapio() {
        // Arrange
        NovoItemCardapioDTO novoItemCardapioDTO = new NovoItemCardapioDTO(
                null,
                "Pizza Margherita",
                "Deliciosa pizza com molho de tomate, mussarela e manjericão.",
                BigDecimal.valueOf(29.90),
                List.of(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-margherita.jpg",
                restaurante.getId()
        );

        when(itemCardapioDataSource.adicionaItemCardapio(any())).thenReturn(itemCardapio);
        when(restauranteDataSource.buscaRestaurantePorId(restaurante.getId())).thenReturn(java.util.Optional.of(restaurante));

        // Act
        ItemCardapioDTO resultado = controller.criaItemCardapio(novoItemCardapioDTO);

        // Assert
        assertNotNull(resultado);
        verify(itemCardapioDataSource, times(1)).adicionaItemCardapio(any());
    }

    @Test
    void deveBuscarItemCardapioPorId() {
        // Arrange
        Long id = 1L;
        NovoItemCardapioDTO novoItemCardapioDTO = new NovoItemCardapioDTO(
                null,
                "Pizza Margherita",
                "Deliciosa pizza com molho de tomate, mussarela e manjericão.",
                BigDecimal.valueOf(29.90),
                List.of(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-margherita.jpg",
                restaurante.getId()
        );

        when(itemCardapioDataSource.buscaItemCardapioPorId(id)).thenReturn(java.util.Optional.of(itemCardapio));

        // Act
        ItemCardapioDTO resultado = controller.buscaItemCardapioPorId(id);

        // Assert
        assertNotNull(resultado);
        verify(itemCardapioDataSource, times(1)).buscaItemCardapioPorId(id);
    }

    @Test
    void deveBuscarItensCardapioPorRestaurante() {
        // Arrange
        Long idRestaurante = 1L;
        List<ItemCardapio> itens = Arrays.asList(itemCardapio, itemCardapio);
        when(itemCardapioDataSource.buscaItensCardapioPorRestaurante(idRestaurante)).thenReturn(itens);

        // Act
        List<ItemCardapioDTO> resultado = controller.buscaItensCardapioPorRestaurante(idRestaurante);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(itemCardapioDataSource, times(1)).buscaItensCardapioPorRestaurante(idRestaurante);
    }

    @Test
    void deveBuscarTodosItensCardapio() {
        // Arrange
        int page = 0;
        int size = 10;
        List<ItemCardapio> itens = Arrays.asList(itemCardapio, itemCardapio);
        when(itemCardapioDataSource.buscaTodosItensCardapio(page, size)).thenReturn(itens);

        // Act
        List<ItemCardapioDTO> resultado = controller.buscaTodosItensCardapio(page, size);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(itemCardapioDataSource, times(1)).buscaTodosItensCardapio(page, size);
    }

    @Test
    void deveAtualizarItemCardapio() {
        // Arrange
        Long id = 1L;
        AtualizaItemCardapioDTO atualizaItemCardapioDTO = new AtualizaItemCardapioDTO(
                "Pizza Calabresa",
                "Pizza com muita calabresa e cebola",
                BigDecimal.valueOf(39.90),
                Arrays.asList(DisponibilidadeConsumoPedidoEnum.LOCAL),
                "https://example.com/pizza-calabresa.jpg",
                restaurante.getId()
        );

        when(itemCardapioDataSource.buscaItemCardapioPorId(id)).thenReturn(java.util.Optional.of(itemCardapio));

        // Act
        controller.atualizaItemCardapio(id, atualizaItemCardapioDTO);

        // Assert
        verify(itemCardapioDataSource, times(1)).buscaItemCardapioPorId(id);
        verify(itemCardapioDataSource, times(1)).atualizaItemCardapio(any());
    }

    @Test
    void deveDeletarItemCardapio() {
        // Arrange
        Long id = 1L;
        when(itemCardapioDataSource.buscaItemCardapioPorId(id)).thenReturn(java.util.Optional.of(itemCardapio));

        // Act
        controller.deletaItemCardapio(id);

        // Assert
        verify(itemCardapioDataSource, times(1)).buscaItemCardapioPorId(id);
        verify(itemCardapioDataSource, times(1)).deletaItemCardapio(any());
    }
}