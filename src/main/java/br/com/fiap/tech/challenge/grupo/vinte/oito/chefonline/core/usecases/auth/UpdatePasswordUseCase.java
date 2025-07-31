package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.auth;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.AuthenticationServicePort;

public class UpdatePasswordUseCase {
    private final AuthenticationServicePort authenticationService;

    public UpdatePasswordUseCase(AuthenticationServicePort authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void executar(String login, String senhaAtual, String novaSenha) {
        authenticationService.validateCurrentPassword(login, senhaAtual);
        authenticationService.updatePassword(login, novaSenha);
    }
}
