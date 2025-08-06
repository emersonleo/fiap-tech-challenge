package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.EmailJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.LoginJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriaClienteUseCaseTest {

    @Mock
    private IClienteGateway clienteGateway;

    @Mock
    private IUsuarioGateway usuarioGateway;

    private CriaClienteUseCase criaClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criaClienteUseCase = CriaClienteUseCase.create(clienteGateway, usuarioGateway);


    }

    @Test
    @DisplayName("Deve criar um cliente com sucesso")
    void deveCriarClienteComSucesso() {
        // Arrange
        when(usuarioGateway.buscaUsuarioPorEmail(anyString())).thenReturn(Optional.empty());
        when(usuarioGateway.buscaUsuarioPorLogin(anyString())).thenReturn(Optional.empty());
        when(clienteGateway.adicionaCliente(any())).thenReturn(new Cliente(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null)
        );

        //act
        Cliente resultado = criaClienteUseCase.run(new NovoClienteDTO(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123"
        ));

        //assert
        assertNotNull(resultado);
        verify(clienteGateway).adicionaCliente(argThat(cliente ->
                cliente.getNome().equals("João da Silva") &&
                        cliente.getEmail().equals("joao.silva@teste.com") &&
                        cliente.getLogin().equals("joao.silva") &&
                        cliente.getSenha().equals("senha123") &&
                        cliente.getEndereco().equals("Rua Exemplo, 123")
        ));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o email já estiver cadastrado")
    void deveLancarExcecaoQuandoEmailJaCadastrado() {
        // Arrange
        String emailExistente = "joao.silva@teste.com";
        when(usuarioGateway.buscaUsuarioPorEmail(emailExistente)).thenReturn(Optional.of(new Cliente(
                null,
                "João da Silva",
                emailExistente,
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null)
        ));

        NovoClienteDTO novoClienteDTO = new NovoClienteDTO(
                null,
                "João da Silva",
                emailExistente,
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123"
        );

        // Act & Assert
        assertThrows(EmailJaCadastrado.class, () -> criaClienteUseCase.run(novoClienteDTO));

        verify(usuarioGateway).buscaUsuarioPorEmail(emailExistente);
        verify(clienteGateway, never()).adicionaCliente(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o login já estiver cadastrado")
    void deveLancarExcecaoQuandoLoginJaCadastrado() {
        //arrange
        String loginExistente = "joao.silva";
        when(usuarioGateway.buscaUsuarioPorEmail(anyString())).thenReturn(Optional.empty());
        when(usuarioGateway.buscaUsuarioPorLogin(loginExistente)).thenReturn(Optional.of(new Cliente(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null)
        ));

        NovoClienteDTO novoClienteDTO = new NovoClienteDTO(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                loginExistente,
                "senha123",
                "Rua Exemplo, 123");

        // Act & Assert
        assertThrows(LoginJaCadastrado.class, () -> criaClienteUseCase.run(novoClienteDTO));

        verify(usuarioGateway).buscaUsuarioPorLogin(loginExistente);
        verify(clienteGateway, never()).adicionaCliente(any());
    }

}
