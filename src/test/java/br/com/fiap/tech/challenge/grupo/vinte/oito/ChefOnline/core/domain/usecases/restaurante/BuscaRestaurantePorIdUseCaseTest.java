package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaRestaurantePorIdUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    private BuscaRestaurantePorIdUseCase buscaRestaurantePorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscaRestaurantePorIdUseCase = BuscaRestaurantePorIdUseCase.create(restauranteGateway);
    }

    @Test
    @DisplayName("Deve buscar restaurante por ID com sucesso")
    void deveBuscarRestaurantePorIdComSucesso() {
        // arrange
        Long id = 1L;
        when(restauranteGateway.buscaRestaurantePorId(id)).thenReturn(
                Optional.of(new Restaurante(
                        id,
                        "Restaurante Teste",
                        "Descrição do restaurante",
                        "Rua Exemplo, 123",
                        "12345678900",
                        null
                )));

        // act
        Restaurante restaurante = buscaRestaurantePorIdUseCase.run(id);

        // assert
        assertNotNull(restaurante);
        verify(restauranteGateway).buscaRestaurantePorId(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não encontrado")
    void deveRetornarExcecaoQuandoRestauranteNaoEncontrado() {
        // arrange
        Long id = 1L;
        when(restauranteGateway.buscaRestaurantePorId(id)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(RestauranteNotFoundException.class, () -> buscaRestaurantePorIdUseCase.run(id));
        verify(restauranteGateway).buscaRestaurantePorId(id);
    }

}
