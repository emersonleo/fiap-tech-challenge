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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeletaClienteUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    private DeletaClienteUseCase deletaClienteUseCase;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        deletaClienteUseCase = DeletaClienteUseCase.create(clienteGateway);

        cliente = new Cliente(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);
    }

    @Test
    @DisplayName("Deve deletar um cliente com sucesso")
    public void deveDeletarClienteComSucesso() {
        // arrange
        Long clienteId = 1L;
        when(clienteGateway.buscaClientePorId(clienteId)).thenReturn(Optional.ofNullable(cliente));

        // act
        deletaClienteUseCase.run(clienteId);

        // assert
        verify(clienteGateway).deletaCliente(argThat(c -> c.getId().equals(clienteId)));
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não encontrado")
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        // arrange
        Long clienteId = 1L;
        when(clienteGateway.buscaClientePorId(clienteId)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ClienteNotFoundException.class, () -> deletaClienteUseCase.run(clienteId));

        // verify that the delete method was never called
        verify(clienteGateway, never()).deletaCliente(any());
    }

}
