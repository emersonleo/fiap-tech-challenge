package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AtualizaRestauranteUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    private AtualizaRestauranteUseCase atualizaRestauranteUseCase;

    private Proprietario proprietario;

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        atualizaRestauranteUseCase = AtualizaRestauranteUseCase.create(restauranteGateway);

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
                "Endereço Teste",
                "Cozinha Teste",
                "Horário Teste",
                proprietario
        );
    }

    @Test
    @DisplayName("Deve atualizar um restaurante com sucesso")
    void deveAtualizarRestauranteComSucesso() {
        // Arrange
        Long idRestaurante = 1L;
        when(restauranteGateway.buscaRestaurantePorId(idRestaurante)).thenReturn(Optional.of(restaurante));
        AtualizaRestauranteDTO atualizaRestauranteDTO = new AtualizaRestauranteDTO(
                "Novo Nome",
                "Novo Endereço",
                "Nova Cozinha",
                "Novo Horário",
                2L
        );

        // Act
        atualizaRestauranteUseCase.run(atualizaRestauranteDTO, idRestaurante);

        // Assert
        verify(restauranteGateway).atualizaRestaurante(argThat(restaurante ->
                restaurante.getNomeRestaurante().equals("Novo Nome") &&
                        restaurante.getEndereco().equals("Novo Endereço") &&
                        restaurante.getTipoCozinha().equals("Nova Cozinha") &&
                        restaurante.getHorarioFuncionamento().equals("Novo Horário") &&
                        restaurante.getProprietario().getId().equals(2L)
        ));
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não for encontrado")
    void deveLancarExcecaoQuandoRestauranteNaoForEncontrado() {
        // Arrange
        Long idRestaurante = 999L; // ID que não existe
        AtualizaRestauranteDTO atualizaRestauranteDTO = new AtualizaRestauranteDTO(
                "Nome Inexistente",
                "Endereço Inexistente",
                "Cozinha Inexistente",
                "Horário Inexistente",
                2L
        );

        when(restauranteGateway.buscaRestaurantePorId(idRestaurante)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RestauranteNotFoundException.class, () -> {
            atualizaRestauranteUseCase.run(atualizaRestauranteDTO, idRestaurante);
        });

        verify(restauranteGateway).buscaRestaurantePorId(idRestaurante);
        verify(restauranteGateway, never()).atualizaRestaurante(any());
    }
}
