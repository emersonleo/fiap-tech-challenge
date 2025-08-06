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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeletaProprietariosUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    private DeletaProprietarioUseCase deletaProprietarioUseCase;

    private Proprietario proprietario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        deletaProprietarioUseCase = DeletaProprietarioUseCase.create(proprietarioGateway);

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
    @DisplayName("Deve deletar um proprietario com sucesso")
    public void deveDeletarProprietarioComSucesso() {
        // arrange
        Long proprietarioId = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(proprietarioId)).thenReturn(Optional.ofNullable(proprietario));

        // act
        deletaProprietarioUseCase.run(proprietarioId);

        // assert
        verify(proprietarioGateway).deletaProprietario(argThat(p -> p.getId().equals(proprietarioId)));
    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietario não encontrado")
    void deveLancarExcecaoQuandoProprietarioNaoEncontrado() {
        // arrange
        Long proprietarioId = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(proprietarioId)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ProprietarioNotFoundException.class, () -> deletaProprietarioUseCase.run(proprietarioId));

        // verify that the delete method was never called
        verify(proprietarioGateway, never()).deletaProprietario(any());
    }

}
