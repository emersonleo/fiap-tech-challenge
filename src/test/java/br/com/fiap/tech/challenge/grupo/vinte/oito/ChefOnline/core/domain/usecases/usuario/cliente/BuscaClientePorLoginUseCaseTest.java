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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaClientePorLoginUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    private BuscaClientePorLoginUseCase buscaClientePorLoginUseCase;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        buscaClientePorLoginUseCase = BuscaClientePorLoginUseCase.create(clienteGateway);

        cliente = new Cliente(1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);
    }

    @Test
    @DisplayName("Deve testar busca cliente por login com sucesso")
    void deveTestarBuscaClientePorLoginComSucesso() {
        //arrange
        String login = "cliente123";
        when(clienteGateway.buscaClientePorLogin(login)).thenReturn(Optional.of(cliente));

        //act
        Cliente resultado = buscaClientePorLoginUseCase.run(login);

        //assert
        assertNotNull(resultado);
        assertEquals(resultado.getLogin(), cliente.getLogin());
    }

    @Test
    @DisplayName("Deve testar busca cliente por login com falha")
    void deveTestarBuscaClientePorLoginComFalha() {
        // arrange
        String login = "clienteInexistente";
        when(clienteGateway.buscaClientePorLogin(login)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ClienteNotFoundException.class, () -> buscaClientePorLoginUseCase.run(login));
        verify(clienteGateway).buscaClientePorLogin(login);
    }

}
