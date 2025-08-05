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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscaTodosItensCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    private BuscaTodosItensCardapioUseCase useCase;

    private Restaurante restaurante;

    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = BuscaTodosItensCardapioUseCase.create(itemCardapioGateway);

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
    @DisplayName("Deve buscar todos os itens do cardápio com paginação")
    void deveBuscarTodosItensCardapioComPaginacao() {
        // arrange
        int page = 0;
        int size = 10;
        List<ItemCardapio> itensEsperados = List.of(
                new ItemCardapio(1L, "Pizza Margherita", "Pizza tradicional", DisponibilidadeConsumoPedidoEnum.LOCAL.toString(), BigDecimal.valueOf(45.90), null, restaurante),
                new ItemCardapio(2L, "Pizza Calabresa", "Pizza com calabresa", DisponibilidadeConsumoPedidoEnum.DELIVERY.toString(), BigDecimal.valueOf(42.90), null, restaurante)
        );

        when(itemCardapioGateway.buscaTodosItensCardapio(page, size))
                .thenReturn(itensEsperados);

        // act
        List<ItemCardapio> resultado = useCase.run(page, size);

        // assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(itensEsperados, resultado);
        verify(itemCardapioGateway).buscaTodosItensCardapio(page, size);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver itens")
    void deveRetornarListaVaziaQuandoNaoHouverItens() {
        // arrange
        int page = 0;
        int size = 10;
        when(itemCardapioGateway.buscaTodosItensCardapio(page, size))
                .thenReturn(List.of());

        // act
        List<ItemCardapio> resultado = useCase.run(page, size);

        // assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(itemCardapioGateway).buscaTodosItensCardapio(page, size);
    }
}