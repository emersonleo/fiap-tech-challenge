package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.auth.InvalidAuthException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioAuthGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LoginProprietariosUseCaseTest {

    @Mock
    private ProprietarioAuthGateway proprietarioAuthGateway;

    private LoginProprietarioUseCase loginProprietarioUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginProprietarioUseCase = LoginProprietarioUseCase.create(proprietarioAuthGateway);
    }

    @Test
    @DisplayName("Deve realizar login com sucesso")
    void deveRealizarLoginComSucesso() {
        // arrange
        when(proprietarioAuthGateway.verificaCredenciais(any())).thenReturn(true);

        // act
        var response = loginProprietarioUseCase.run(new VerificaCredenciaisDTO("usuario", "senha"));
        // assert

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.token());
        Assertions.assertFalse(response.token().isEmpty());
    }

    @Test
    @DisplayName("Deve lançar exceção quando credenciais inválidas")
    void deveLancarExcecaoQuandoCredenciaisInvalidas() {
        // arrange
        when(proprietarioAuthGateway.verificaCredenciais(any())).thenReturn(false);
        // act & assert
        Assertions.assertThrows(
                InvalidAuthException.class,
                () -> loginProprietarioUseCase.run(new VerificaCredenciaisDTO("usuario", "senha"))
        );
    }

}
