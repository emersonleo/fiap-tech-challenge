package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
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

public class BuscaProprietarioPorIdUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    private BuscaProprietarioPorIdUseCase buscaProprietarioPorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        buscaProprietarioPorIdUseCase = BuscaProprietarioPorIdUseCase.create(proprietarioGateway);
    }

    @Test
    @DisplayName("Deve buscar proprietario por ID com sucesso")
    void deveBuscarProprietarioPorIdComSucesso() {
        // arrange
        Long id = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(id)).thenReturn(
                Optional.of(new Proprietario(
                        id,
                        "João da Silva",
                        "joao.silva@teste.com",
                        "joao.silva",
                        "senha123",
                        "Rua Exemplo, 123",
                        null
                )));

        // act
        Proprietario proprietario = buscaProprietarioPorIdUseCase.run(id);

        // assert
        assertNotNull(proprietario);
        verify(proprietarioGateway).buscaProprietarioPorId(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietario não encontrado")
    void deveRetornarExcecaoQuandoProprietarioNaoEncontrado() {
        // arrange
        Long id = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(id)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ProprietarioNotFoundException.class, () -> buscaProprietarioPorIdUseCase.run(id));
    }

}
