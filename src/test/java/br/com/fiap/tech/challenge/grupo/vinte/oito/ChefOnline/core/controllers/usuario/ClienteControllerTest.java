package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.AtualizaClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common.PresenterMasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClienteControllerTest {

    @Mock
    private IClienteDataSource clienteDataSource;

    @Mock
    private IUsuarioDataSource usuarioDataSource;

    private ClienteController clienteController;

    private Cliente cliente;

    private NovoClienteDTO novoClienteDTO;

    private AtualizaClienteDTO atualizaClienteDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteController = new ClienteController(clienteDataSource, usuarioDataSource);
        cliente = new Cliente(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null
        );

        novoClienteDTO = new NovoClienteDTO(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123"
        );

        atualizaClienteDTO = new AtualizaClienteDTO(
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "endereco alterado"
        );
    }

    @Test
    @DisplayName("Deve criar cliente com sucesso")
    void deveCriarClienteComSucesso() {
        // arrange
        when(usuarioDataSource.buscaUsuarioPorEmail(any())).thenReturn(Optional.empty());
        when(clienteDataSource.adicionaCliente(any())).thenReturn(cliente);

        // act
        var resultado = clienteController.criaCliente(novoClienteDTO);

        // assert
        assertNotNull(resultado);
        assertEquals(novoClienteDTO.nome(), resultado.nome());
        assertEquals(PresenterMasks.maskEmail(novoClienteDTO.email()), resultado.email());
        verify(clienteDataSource).adicionaCliente(any());
    }

    @Test
    @DisplayName("Deve buscar todos clientes")
    void deveBuscarTodosClientes() {
        // arrange
        int page = 0;
        int size = 10;
        List<Cliente> clientes = List.of(cliente);

        when(clienteDataSource.buscaTodosClientes(page, size)).thenReturn(clientes);

        // act
        var resultado = clienteController.buscaTodosClientes(page, size);

        // assert
        assertEquals(1, resultado.size());
        assertEquals(cliente.getNome(), resultado.getFirst().nome());
        verify(clienteDataSource).buscaTodosClientes(page, size);
    }

    @Test
    @DisplayName("Deve buscar cliente por ID")
    void deveBuscarClientePorId() {
        // arrange
        Long id = 1L;
        when(clienteDataSource.buscaClientePorId(id)).thenReturn(Optional.of(cliente));

        // act
        var resultado = clienteController.buscaClientePorId(id);

        // assert
        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.nome());
        assertEquals(PresenterMasks.maskEmail(cliente.getEmail()), resultado.email());
        verify(clienteDataSource).buscaClientePorId(id);
    }

    @Test
    @DisplayName("Deve buscar cliente por login")
    void deveBuscarClientePorLogin() {
        // arrange
        String login = "cliente@teste.com";
        when(clienteDataSource.buscaClientePorLogin(login)).thenReturn(Optional.of(cliente));

        // act
        var resultado = clienteController.buscaClientePorLogin(login);

        // assert
        assertNotNull(resultado);
        assertEquals(PresenterMasks.maskEmail(cliente.getEmail()), resultado.email());
        verify(clienteDataSource).buscaClientePorLogin(login);
    }

    @Test
    @DisplayName("Deve atualizar cliente com sucesso")
    void deveAtualizarClienteComSucesso() {
        // arrange
        Long id = 1L;
        when(clienteDataSource.buscaClientePorId(id)).thenReturn(Optional.of(cliente));
        when(clienteDataSource.atualizaCliente(any())).thenReturn(cliente);

        // act
        var resultado = clienteController.atualizaCliente(atualizaClienteDTO, id);

        // assert
        assertNotNull(resultado);
        assertEquals(atualizaClienteDTO.nome(), resultado.nome());
        assertEquals(PresenterMasks.maskEmail(atualizaClienteDTO.email()), resultado.email());
        verify(clienteDataSource).atualizaCliente(any());
    }

    @Test
    @DisplayName("Deve deletar cliente com sucesso")
    void deveDeletarClienteComSucesso() {
        // arrange
        Long id = 1L;
        when(clienteDataSource.buscaClientePorId(id)).thenReturn(Optional.of(cliente));

        // act
        clienteController.deletaCliente(id);

        // assert
        verify(clienteDataSource).deletaCliente(cliente);
    }

    @Test
    @DisplayName("Deve atualizar senha com sucesso")
    void deveAtualizarSenhaComSucesso() {
        // arrange
        String login = "cliente@teste.com";
        TrocaSenhaDTO dto = new TrocaSenhaDTO(login, "senha123", "nova_senha");

        when(clienteDataSource.buscaClientePorLogin(login)).thenReturn(Optional.of(cliente));
        when(clienteDataSource.atualizaCliente(any())).thenReturn(cliente);

        // act
        var resultado = clienteController.atualizaSenha(dto);

        // assert
        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.nome());
        verify(clienteDataSource).atualizaCliente(any());
    }
}