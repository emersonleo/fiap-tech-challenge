package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.SenhaIncorretaException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AtualizaSenhaClienteUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    private AtualizaSenhaClienteUseCase atualizaSenhaClienteUseCase;

    private Cliente clienteSenhaAntiga;

    private Cliente clienteSenhaNova;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        atualizaSenhaClienteUseCase = AtualizaSenhaClienteUseCase.create(clienteGateway);

        clienteSenhaAntiga = new Cliente(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null
        );


        clienteSenhaNova = new Cliente(
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
        when(clienteGateway.buscaClientePorLogin("usuario"))
                .thenReturn(Optional.of(clienteSenhaAntiga));
        when(clienteGateway.atualizaCliente(any(Cliente.class)))
                .thenReturn(clienteSenhaNova);

        // act
        var response = atualizaSenhaClienteUseCase.run(
                new TrocaSenhaDTO("usuario", "senha123", "senha321")
        );

        // assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals("senha321", response.getSenha());
        Assertions.assertEquals(clienteSenhaNova.getId(), response.getId());
        Assertions.assertEquals(clienteSenhaNova.getNome(), response.getNome());
        Assertions.assertEquals(clienteSenhaNova.getEmail(), response.getEmail());
        Assertions.assertEquals(clienteSenhaNova.getLogin(), response.getLogin());
        Assertions.assertEquals(clienteSenhaNova.getEndereco(), response.getEndereco());
        Assertions.assertNull(response.getDataUltimaAlteracao());
        Assertions.assertEquals(clienteSenhaNova.getTipo(), response.getTipo());
        Assertions.assertEquals(clienteSenhaNova.getDataUltimaAlteracao(), response.getDataUltimaAlteracao());

    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não encontrado")
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // arrange
        when(clienteGateway.buscaClientePorLogin("usuario"))
                .thenReturn(Optional.empty());

        // act & assert
        Assertions.assertThrows(
                ClienteNotFoundException.class,
                () -> atualizaSenhaClienteUseCase.run(
                        new TrocaSenhaDTO("usuario", "senha123", "senha321")
                )
        );
    }

    @Test
    @DisplayName("Deve lançar exceção quando senha atual está incorreta")
    void deveLancarExcecaoQuandoSenhaAtualIncorreta() {
        // arrange
        when(clienteGateway.buscaClientePorLogin("usuario"))
                .thenReturn(Optional.of(clienteSenhaAntiga));

        // act & assert
        SenhaIncorretaException exception = Assertions.assertThrows(
                SenhaIncorretaException.class,
                () -> atualizaSenhaClienteUseCase.run(
                        new TrocaSenhaDTO("usuario", "senha_incorreta", "nova_senha")
                )
        );

        Assertions.assertEquals("Senha atual incorreta", exception.getMessage());
    }


}
