package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cardapio.ItemCardapioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscaItemCardapioPorIdUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    private BuscaItemCardapioPorIdUseCase useCase;

    private ItemCardapio itemCardapio;

    private Proprietario proprietario;

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new BuscaItemCardapioPorIdUseCase(itemCardapioGateway);

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
    void deveBuscarItemCardapioQuandoExistir() {
        // Arrange
        Long id = 1L;
        when(itemCardapioGateway.buscaItemCardapioPorId(id)).thenReturn(Optional.of(itemCardapio));

        // Act
        ItemCardapio resultado = useCase.run(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(itemCardapio, resultado);
        verify(itemCardapioGateway, times(1)).buscaItemCardapioPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExistir() {
        // Arrange
        Long id = 1L;
        when(itemCardapioGateway.buscaItemCardapioPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemCardapioNotFoundException.class, () -> useCase.run(id));
        verify(itemCardapioGateway, times(1)).buscaItemCardapioPorId(id);
    }

    @Test
    void deveUsarFactoryMethodParaCriarInstancia() {
        // Act
        BuscaItemCardapioPorIdUseCase novaInstancia = BuscaItemCardapioPorIdUseCase.create(itemCardapioGateway);

        // Assert
        assertNotNull(novaInstancia);
        assertInstanceOf(BuscaItemCardapioPorIdUseCase.class, novaInstancia);
    }
}