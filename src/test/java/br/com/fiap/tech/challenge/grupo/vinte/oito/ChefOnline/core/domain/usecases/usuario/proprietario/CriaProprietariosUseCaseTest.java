package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.EmailJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.LoginJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
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

class CriaProprietariosUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    @Mock
    private IUsuarioGateway usuarioGateway;

    private CriaProprietarioUseCase criaProprietarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        criaProprietarioUseCase = CriaProprietarioUseCase.create(proprietarioGateway, usuarioGateway);
    }

    @Test
    @DisplayName("Deve criar um proprietario com sucesso")
    void deveCriarProprietarioComSucesso() {
        // Arrange
        when(usuarioGateway.buscaUsuarioPorEmail(anyString())).thenReturn(Optional.empty());
        when(usuarioGateway.buscaUsuarioPorLogin(anyString())).thenReturn(Optional.empty());
        when(proprietarioGateway.adicionaProprietario(any())).thenReturn(new Proprietario(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null)
        );

        //act
        Proprietario resultado = criaProprietarioUseCase.run(new NovoProprietarioDTO(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123"
        ));

        //assert
        assertNotNull(resultado);
        verify(proprietarioGateway).adicionaProprietario(argThat(cliente ->
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

        NovoProprietarioDTO novoProprietarioDTO = new NovoProprietarioDTO(
                null,
                "João da Silva",
                emailExistente,
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123"
        );

        // Act & Assert
        assertThrows(EmailJaCadastrado.class, () -> criaProprietarioUseCase.run(novoProprietarioDTO));

        verify(usuarioGateway).buscaUsuarioPorEmail(emailExistente);
        verify(proprietarioGateway, never()).adicionaProprietario(any());
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

        NovoProprietarioDTO novoProprietarioDTO = new NovoProprietarioDTO(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                loginExistente,
                "senha123",
                "Rua Exemplo, 123");

        // Act & Assert
        assertThrows(LoginJaCadastrado.class, () -> criaProprietarioUseCase.run(novoProprietarioDTO));

        verify(usuarioGateway).buscaUsuarioPorLogin(loginExistente);
        verify(proprietarioGateway, never()).adicionaProprietario(any());
    }

}
