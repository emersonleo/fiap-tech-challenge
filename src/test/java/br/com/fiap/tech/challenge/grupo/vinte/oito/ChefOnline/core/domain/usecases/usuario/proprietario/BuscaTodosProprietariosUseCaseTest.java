package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class BuscaTodosProprietariosUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    private BuscaTodosProprietariosUseCase buscaTodosProprietariosUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscaTodosProprietariosUseCase = BuscaTodosProprietariosUseCase.create(proprietarioGateway);
    }

    @Test
    @DisplayName("Deve buscar todos os proprietarios com sucesso")
    void deveBuscarTodosProprietariosComSucesso() {
        // arrange
        int page = 0;
        int size = 10;

        // act
        List<Proprietario> proprietarios = buscaTodosProprietariosUseCase.run(page, size);

        // assert
        assertNotNull(proprietarios);
        verify(proprietarioGateway).buscaTodosProprietarios(page, size);
    }

}
