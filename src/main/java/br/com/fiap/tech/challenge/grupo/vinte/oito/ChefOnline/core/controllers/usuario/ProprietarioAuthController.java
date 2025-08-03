package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.LoginProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.LoginResponseProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioAuthGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.proprietario.IProprietarioAuthDataSource;

public class ProprietarioAuthController {
    private final IProprietarioAuthDataSource proprietarioAuthDataSource;

    public ProprietarioAuthController(IProprietarioAuthDataSource proprietarioAuthDataSource) {
        this.proprietarioAuthDataSource = proprietarioAuthDataSource;
    }

    public LoginResponseProprietarioDTO login(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        var gateway = ProprietarioAuthGateway.create(proprietarioAuthDataSource);
        var loginUseCase = LoginProprietarioUseCase.create(gateway);
        return loginUseCase.run(verificaCredenciaisDTO);
    }
}
