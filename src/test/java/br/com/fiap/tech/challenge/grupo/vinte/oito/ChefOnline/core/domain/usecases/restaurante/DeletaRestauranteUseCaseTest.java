package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeletaRestauranteUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    private DeletaRestauranteUseCase deletaRestauranteUseCase;

    private Restaurante restaurante;

    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        deletaRestauranteUseCase = DeletaRestauranteUseCase.create(restauranteGateway);

        proprietario = new Proprietario(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);

        restaurante = new Restaurante(
                1L,
                "Restaurante Teste",
                "Descrição",
                "Endereço",
                "11:00 as 22:00",
                proprietario);

    }

    @Test
    @DisplayName("Deve deletar um restaurante com sucesso")
    void deveDeletarRestauranteComSucesso() {
        // arrange
        Long restauranteId = 1L;
        when(restauranteGateway.buscaRestaurantePorId(restauranteId))
                .thenReturn(java.util.Optional.of(restaurante));

        // act
        deletaRestauranteUseCase.run(restauranteId);

        // assert
        verify(restauranteGateway).deletaRestaurante(argThat(restaurante -> restaurante.getId().equals(restauranteId)));
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não encontrado")
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        // arrange
        Long restauranteId = 1L;

        // act & assert
        assertThrows(RestauranteNotFoundException.class, () -> deletaRestauranteUseCase.run(restauranteId));

        // verify that the delete method was never called
        verify(restauranteGateway, never()).deletaRestaurante(any());
    }

}
