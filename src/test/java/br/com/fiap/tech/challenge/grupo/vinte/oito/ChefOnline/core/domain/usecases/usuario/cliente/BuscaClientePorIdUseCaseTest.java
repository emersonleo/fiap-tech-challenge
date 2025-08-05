package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;
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

public class BuscaClientePorIdUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    private BuscaClientePorIdUseCase buscaClientePorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        buscaClientePorIdUseCase = BuscaClientePorIdUseCase.create(clienteGateway);
    }

    @Test
    @DisplayName("Deve buscar cliente por ID com sucesso")
    void deveBuscarClientePorIdComSucesso() {
        // arrange
        Long id = 1L;
        when(clienteGateway.buscaClientePorId(id)).thenReturn(
                Optional.of(new Cliente(
                        id,
                        "João da Silva",
                        "joao.silva@teste.com",
                        "joao.silva",
                        "senha123",
                        "Rua Exemplo, 123",
                        null
                )));

        // act
        Cliente cliente = buscaClientePorIdUseCase.run(id);

        // assert
        assertNotNull(cliente);
        verify(clienteGateway).buscaClientePorId(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não encontrado")
    void deveRetornarExcecaoQuandoClienteNaoEncontrado() {
        // arrange
        Long id = 1L;
        when(clienteGateway.buscaClientePorId(id)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ClienteNotFoundException.class, () -> buscaClientePorIdUseCase.run(id));
    }

}
