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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaProprietarioPorLoginUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    private BuscaProprietarioPorLoginUseCase buscaProprietarioPorLoginUseCase;

    private Proprietario proprietario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        buscaProprietarioPorLoginUseCase = BuscaProprietarioPorLoginUseCase.create(proprietarioGateway);

        proprietario = new Proprietario(1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);
    }

    @Test
    @DisplayName("Deve testar busca proprietario por login com sucesso")
    void deveTestarBuscaProprietarioPorLoginComSucesso() {
        //arrange
        String login = "cliente123";
        when(proprietarioGateway.buscaProprietarioPorLogin(login)).thenReturn(Optional.of(proprietario));

        //act
        Proprietario resultado = buscaProprietarioPorLoginUseCase.run(login);

        //assert
        assertNotNull(resultado);
        assertEquals(resultado.getLogin(), proprietario.getLogin());
    }

    @Test
    @DisplayName("Deve testar busca proprietario por login com falha")
    void deveTestarBuscaProprietarioPorLoginComFalha() {
        // arrange
        String login = "clienteInexistente";
        when(proprietarioGateway.buscaProprietarioPorLogin(login)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ProprietarioNotFoundException.class, () -> buscaProprietarioPorLoginUseCase.run(login));
        verify(proprietarioGateway).buscaProprietarioPorLogin(login);
    }

}
