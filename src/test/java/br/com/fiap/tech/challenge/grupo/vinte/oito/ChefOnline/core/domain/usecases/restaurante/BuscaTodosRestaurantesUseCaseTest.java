package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class BuscaTodosRestaurantesUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    private BuscaTodosRestaurantesUseCase buscaTodosRestaurantesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscaTodosRestaurantesUseCase = BuscaTodosRestaurantesUseCase.create(restauranteGateway);
    }

    @Test
    @DisplayName("Deve buscar todos os restaurantes com sucesso")
    void deveBuscarTodosRestaurantesComSucesso() {
        // arrange
        int page = 0;
        int size = 10;

        // act
        List<Restaurante> restaurantes = buscaTodosRestaurantesUseCase.run(page, size);

        // assert
        assertNotNull(restaurantes);
        verify(restauranteGateway).buscaTodosRestaurantes(page, size);
    }

}
