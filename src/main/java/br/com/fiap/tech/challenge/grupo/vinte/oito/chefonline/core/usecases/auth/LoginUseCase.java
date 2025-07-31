package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.auth;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.AuthenticationServicePort;

public class LoginUseCase {
    private final AuthenticationServicePort authenticationService;

    public LoginUseCase(AuthenticationServicePort authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void executar(String login, String senha) {
        authenticationService.authenticate(login, senha);
    }
}
