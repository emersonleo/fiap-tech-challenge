package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ProprietarioAuthController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.LoginResponseProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.proprietario.IProprietarioAuthDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProprietarioAuthControllerTest {

    @Mock
    private IProprietarioAuthDataSource proprietarioAuthDataSource;

    private ProprietarioAuthController proprietarioAuthController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proprietarioAuthController = new ProprietarioAuthController(proprietarioAuthDataSource);
    }

    @Test
    @DisplayName("Deve realizar login com sucesso")
    void deveRealizarLoginComSucesso() {
        // arrange
        String email = "proprietario@teste.com";
        String senha = "senha123";
        VerificaCredenciaisDTO credenciais = new VerificaCredenciaisDTO(email, senha);

        when(proprietarioAuthDataSource.verificaCredenciais(credenciais)).thenReturn(true);

        // act
        LoginResponseProprietarioDTO resultado = proprietarioAuthController.login(credenciais);

        // assert
        assertNotNull(resultado);
        verify(proprietarioAuthDataSource).verificaCredenciais(credenciais);
    }

}