package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.DisponibilidadeConsumoPedidoEnum;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaItensCardapioPorRestauranteUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    private BuscaItensCardapioPorRestauranteUseCase useCase;

    private Restaurante restaurante;

    private Proprietario proprietario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        useCase = BuscaItensCardapioPorRestauranteUseCase.create(itemCardapioGateway);

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
    }

    @Test
    @DisplayName("Deve buscar itens do cardápio por restaurante com sucesso")
    void deveBuscarItensCardapioPorRestauranteComSucesso() {
        // arrange
        Long restauranteId = 1L;
        List<ItemCardapio> itensEsperados = List.of(
                new ItemCardapio(1L, "Pizza Margherita", "Pizza tradicional", DisponibilidadeConsumoPedidoEnum.LOCAL.toString(), BigDecimal.valueOf(45.90), null, restaurante),
                new ItemCardapio(2L, "Pizza Calabresa", "Pizza com calabresa", DisponibilidadeConsumoPedidoEnum.DELIVERY.toString(), BigDecimal.valueOf(42.90), null, restaurante)
        );

        when(itemCardapioGateway.buscaItensCardapioPorRestaurante(restauranteId))
                .thenReturn(itensEsperados);

        // act
        List<ItemCardapio> resultado = useCase.run(restauranteId);

        // assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(itensEsperados, resultado);
        verify(itemCardapioGateway).buscaItensCardapioPorRestaurante(restauranteId);
    }

}
