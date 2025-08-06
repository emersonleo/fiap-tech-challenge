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

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeletaItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    private DeletaItemCardapioUseCase useCase;

    private ItemCardapio itemCardapio;

    private Restaurante restaurante;

    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new DeletaItemCardapioUseCase(itemCardapioGateway);

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
    void deveDeletarItemCardapioQuandoExistir() {
        // Arrange
        Long id = 1L;
        when(itemCardapioGateway.buscaItemCardapioPorId(id)).thenReturn(Optional.of(itemCardapio));
        doNothing().when(itemCardapioGateway).deletaItemCardapio(itemCardapio);

        // Act
        useCase.run(id);

        // Assert
        verify(itemCardapioGateway, times(1)).buscaItemCardapioPorId(id);
        verify(itemCardapioGateway, times(1)).deletaItemCardapio(itemCardapio);
    }

    @Test
    void deveLancarExcecaoQuandoItemNaoExistir() {
        // Arrange
        Long id = 1L;
        when(itemCardapioGateway.buscaItemCardapioPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ItemCardapioNotFoundException.class, () -> useCase.run(id));
        verify(itemCardapioGateway, times(1)).buscaItemCardapioPorId(id);
        verify(itemCardapioGateway, never()).deletaItemCardapio(any());
    }

    @Test
    void deveUsarFactoryMethodParaCriarInstancia() {
        // Act
        DeletaItemCardapioUseCase novaInstancia = DeletaItemCardapioUseCase.create(itemCardapioGateway);

        // Assert
        assertNotNull(novaInstancia);
        assertInstanceOf(DeletaItemCardapioUseCase.class, novaInstancia);
    }
}