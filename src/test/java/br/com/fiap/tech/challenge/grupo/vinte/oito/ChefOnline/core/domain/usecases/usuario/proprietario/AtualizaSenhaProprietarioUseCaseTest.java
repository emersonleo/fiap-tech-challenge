package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.SenhaIncorretaException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AtualizaSenhaProprietarioUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    private AtualizaSenhaProprietarioUseCase atualizaSenhaProprietarioUseCase;

    private Proprietario proprietarioSenhaAntiga;

    private Proprietario proprietarioSenhaNova;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        atualizaSenhaProprietarioUseCase = AtualizaSenhaProprietarioUseCase.create(proprietarioGateway);

        proprietarioSenhaAntiga = new Proprietario(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null
        );


        proprietarioSenhaNova = new Proprietario(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha321",
                "Rua Exemplo, 123",
                null
        );
    }

    @Test
    @DisplayName("Deve atualizar senha com sucesso")
    void deveAtualizarSenhaComSucesso() {
        // arrange
        when(proprietarioGateway.buscaProprietarioPorLogin("usuario"))
                .thenReturn(Optional.of(proprietarioSenhaAntiga));
        when(proprietarioGateway.atualizaProprietario(any(Proprietario.class)))
                .thenReturn(proprietarioSenhaNova);

        // act
        var response = atualizaSenhaProprietarioUseCase.run(
                new TrocaSenhaDTO("usuario", "senha123", "senha321")
        );

        // assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals("senha321", response.getSenha());
        Assertions.assertEquals(proprietarioSenhaNova.getId(), response.getId());
        Assertions.assertEquals(proprietarioSenhaNova.getNome(), response.getNome());
        Assertions.assertEquals(proprietarioSenhaNova.getEmail(), response.getEmail());
        Assertions.assertEquals(proprietarioSenhaNova.getLogin(), response.getLogin());
        Assertions.assertEquals(proprietarioSenhaNova.getEndereco(), response.getEndereco());
        Assertions.assertNull(response.getDataUltimaAlteracao());
        Assertions.assertEquals(proprietarioSenhaNova.getTipo(), response.getTipo());
        Assertions.assertEquals(proprietarioSenhaNova.getDataUltimaAlteracao(), response.getDataUltimaAlteracao());

    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietario não encontrado")
    void deveLancarExcecaoQuandoProprietarioNaoEncontrado() {
        // arrange
        when(proprietarioGateway.buscaProprietarioPorLogin("usuario"))
                .thenReturn(Optional.empty());

        // act & assert
        Assertions.assertThrows(
                ProprietarioNotFoundException.class,
                () -> atualizaSenhaProprietarioUseCase.run(
                        new TrocaSenhaDTO("usuario", "senha123", "senha321")
                )
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha atual está incorreta")
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {
        // arrange
        when(proprietarioGateway.buscaProprietarioPorLogin("usuario"))
                .thenReturn(Optional.of(proprietarioSenhaAntiga));

        // act & assert
        SenhaIncorretaException exception = Assertions.assertThrows(
                SenhaIncorretaException.class,
                () -> atualizaSenhaProprietarioUseCase.run(
                        new TrocaSenhaDTO("usuario", "senha_incorreta", "nova_senha")
                )
        );

        Assertions.assertEquals("Senha atual incorreta", exception.getMessage());
    }

}
