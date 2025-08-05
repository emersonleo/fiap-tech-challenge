package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.LoginResponseClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClienteAuthControllerTest {

    @Mock
    private IClienteAuthDataSource clienteAuthDataSource;

    private ClienteAuthController clienteAuthController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteAuthController = new ClienteAuthController(clienteAuthDataSource);
    }

    @Test
    @DisplayName("Deve realizar login com sucesso")
    void deveRealizarLoginComSucesso() {
        // arrange
        String email = "cliente@teste.com";
        String senha = "senha123";
        VerificaCredenciaisDTO credenciais = new VerificaCredenciaisDTO(email, senha);

        when(clienteAuthDataSource.verificaCredenciais(credenciais)).thenReturn(true);

        // act
        LoginResponseClienteDTO resultado = clienteAuthController.login(credenciais);

        // assert
        assertNotNull(resultado);
        verify(clienteAuthDataSource).verificaCredenciais(credenciais);
    }

}
