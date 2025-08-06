package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.AtualizaClienteDTO;
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

public class AtualizaClienteUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    private AtualizaClienteUseCase atualizaClienteUseCase;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        atualizaClienteUseCase = AtualizaClienteUseCase.create(clienteGateway);

        cliente = new Cliente(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);
    }

    @Test
    @DisplayName("Deve atualizar um cliente com sucesso")
    void deveAtualizarClienteComSucesso() {
        //arrange
        Long idCliente = 1L;
        when(clienteGateway.buscaClientePorId(idCliente)).thenReturn(Optional.of(cliente));
        AtualizaClienteDTO atualizaClienteDTO = new AtualizaClienteDTO(
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "Rua Exemplo, 123"
        );

        //act
        atualizaClienteUseCase.run(atualizaClienteDTO, idCliente);

        //assert
        verify(clienteGateway).atualizaCliente(argThat(c ->
                c.getNome().equals(atualizaClienteDTO.nome()) &&
                        c.getEmail().equals(atualizaClienteDTO.email()) &&
                        c.getLogin().equals(atualizaClienteDTO.login()) &&
                        c.getEndereco().equals(atualizaClienteDTO.endereco())
        ));
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não existir")
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        // Arrange
        Long idCliente = 1L;
        when(clienteGateway.buscaClientePorId(idCliente)).thenReturn(Optional.empty());
        AtualizaClienteDTO atualizaClienteDTO = new AtualizaClienteDTO(
                "João da Silva",
                "joao.silva@email.com",
                "joao.silva",
                "Rua Exemplo, 123"
        );

        when(clienteGateway.buscaClientePorId(idCliente)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ClienteNotFoundException.class, () -> {
            atualizaClienteUseCase.run(atualizaClienteDTO, idCliente);
        });

        verify(clienteGateway).buscaClientePorId(idCliente);
        verify(clienteGateway, never()).atualizaCliente(any());
    }

}
