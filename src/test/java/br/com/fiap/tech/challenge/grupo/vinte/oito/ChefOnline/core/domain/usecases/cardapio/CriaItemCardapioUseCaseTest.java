package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cardapio;

//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.ICardapioGateway;
//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioGateway;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;

class CriaItemCardapioUseCaseTest {

//    @Mock
//    private IItemCardapioGateway itemCardapioGateway;
//
//    private CardapioService cardapioService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        cardapioService = new CardapioService(cardapioGateway);
//    }
//
//    @Test
//    @DisplayName("Deve adicionar item ao cardápio com sucesso")
//    void deveAdicionarItemAoCardapioComSucesso() {
//        // arrange
//        ItemCardapio item = new ItemCardapio();
//        item.setNome("X-Burger");
//        item.setPreco(BigDecimal.valueOf(25.90));
//        when(cardapioGateway.save(any())).thenReturn(item);
//
//        // act
//        ItemCardapio resultado = cardapioService.adicionarItem(item);
//
//        // assert
//        assertNotNull(resultado);
//        assertEquals("X-Burger", resultado.getNome());
//        verify(cardapioGateway).save(item);
//    }
//
//    @Test
//    @DisplayName("Deve buscar item do cardápio por ID")
//    void deveBuscarItemPorId() {
//        // arrange
//        Long itemId = 1L;
//        ItemCardapio item = new ItemCardapio();
//        when(cardapioGateway.findById(itemId)).thenReturn(Optional.of(item));
//
//        // act
//        Optional<ItemCardapio> resultado = cardapioService.buscarPorId(itemId);
//
//        // assert
//        assertTrue(resultado.isPresent());
//        verify(cardapioGateway).findById(itemId);
//    }
}
