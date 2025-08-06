package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriaRestauranteUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    @Mock
    private IRestauranteGateway restauranteGateway;

    private Proprietario proprietario;

    private CriaRestauranteUseCase criaRestauranteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criaRestauranteUseCase = CriaRestauranteUseCase.create(proprietarioGateway, restauranteGateway);

        proprietario = new Proprietario(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);
    }

    @Test
    @DisplayName("Deve criar um restaurante com sucesso")
    void deveCriarRestauranteComSucesso() {
        // arrange
        Long proprietarioId = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(proprietarioId)).thenReturn(Optional.of(proprietario));
        when(restauranteGateway.adicionaRestaurante(any())).thenReturn(new Restaurante(
                null,
                "Restaurante Teste",
                "Rua Exemplo, 123",
                "Italiana",
                "11:00 - 22:00",
                proprietario
        ));

        // act
        Restaurante resultado = criaRestauranteUseCase.run(new NovoRestauranteDTO(
                "Restaurante Teste",
                "Rua Exemplo, 123",
                "Italiana",
                "11:00 - 22:00",
                proprietarioId
        ));

        // assert
        assertNotNull(resultado);
        verify(restauranteGateway).adicionaRestaurante(argThat(restaurante ->
                restaurante.getNomeRestaurante().equals("Restaurante Teste") &&
                        restaurante.getEndereco().equals("Rua Exemplo, 123") &&
                        restaurante.getTipoCozinha().equals("Italiana") &&
                        restaurante.getHorarioFuncionamento().equals("11:00 - 22:00")
        ));
    }

    @Test
    @DisplayName("Deve lançar ProprietarioNotFoundException quando proprietário não for encontrado")
    void deveLancarExcecaoQuandoProprietarioNaoForEncontrado() {
        // arrange
        Long proprietarioId = 999L;
        NovoRestauranteDTO dto = new NovoRestauranteDTO(
                "Restaurante Teste",
                "Rua Exemplo, 123",
                "Italiana",
                "11:00 - 22:00",
                proprietarioId
        );

        when(proprietarioGateway.buscaProprietarioPorId(proprietarioId)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ProprietarioNotFoundException.class, () ->
                criaRestauranteUseCase.run(dto)
        );

        verify(proprietarioGateway).buscaProprietarioPorId(proprietarioId);
        verify(restauranteGateway, never()).adicionaRestaurante(any());
    }

}
