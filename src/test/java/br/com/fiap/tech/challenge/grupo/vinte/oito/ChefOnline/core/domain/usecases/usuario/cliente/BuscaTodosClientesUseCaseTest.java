package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class BuscaTodosClientesUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    private BuscaTodosClientesUseCase buscaTodosClientesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buscaTodosClientesUseCase = BuscaTodosClientesUseCase.create(clienteGateway);
    }

    @Test
    @DisplayName("Deve buscar todos os clientes com sucesso")
    void deveBuscarTodosClientesComSucesso() {
        // arrange
        int page = 0;
        int size = 10;

        // act
        List<Cliente> clientes = buscaTodosClientesUseCase.run(page, size);

        // assert
        assertNotNull(clientes);
        verify(clienteGateway).buscaTodosClientes(page, size);
    }

}
