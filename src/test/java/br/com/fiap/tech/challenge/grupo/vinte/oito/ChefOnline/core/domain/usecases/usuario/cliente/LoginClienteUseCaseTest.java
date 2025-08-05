package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.auth.InvalidAuthException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoginClienteUseCaseTest {

    @Mock
    private IClienteAuthGateway clienteAuthGateway;

    private LoginClienteUseCase loginClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginClienteUseCase = LoginClienteUseCase.create(clienteAuthGateway);
    }

    @Test
    @DisplayName("Deve realizar login com sucesso")
    void deveRealizarLoginComSucesso() {
        // arrange
        when(clienteAuthGateway.verificaCredenciais(any())).thenReturn(true);

        // act
        var response = loginClienteUseCase.run(new VerificaCredenciaisDTO("usuario", "senha"));
        // assert

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.token());
        Assertions.assertFalse(response.token().isEmpty());
    }

    @Test
    @DisplayName("Deve lançar exceção quando credenciais inválidas")
    void deveLancarExcecaoQuandoCredenciaisInvalidas() {
        // arrange
        when(clienteAuthGateway.verificaCredenciais(any())).thenReturn(false);
        // act & assert
        Assertions.assertThrows(
                InvalidAuthException.class,
                () -> loginClienteUseCase.run(new VerificaCredenciaisDTO("usuario", "senha"))
        );
    }

}
