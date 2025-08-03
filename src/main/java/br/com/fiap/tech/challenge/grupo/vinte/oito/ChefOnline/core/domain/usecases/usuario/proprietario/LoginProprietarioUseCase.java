package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.LoginResponseProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.auth.InvalidAuthException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioAuthGateway;

import java.util.Date;

public class LoginProprietarioUseCase {
    private final ProprietarioAuthGateway proprietarioAuthGateway;

    private LoginProprietarioUseCase(ProprietarioAuthGateway proprietarioAuthGateway) {
        this.proprietarioAuthGateway = proprietarioAuthGateway;
    }

    public static LoginProprietarioUseCase create(ProprietarioAuthGateway proprietarioAuthGateway) {
        return new LoginProprietarioUseCase(proprietarioAuthGateway);
    }

    public LoginResponseProprietarioDTO run(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        if (!this.proprietarioAuthGateway.verificaCredenciais(verificaCredenciaisDTO)) {
            throw new InvalidAuthException(NomeDoTipo.PROPRIETARIO);
        }

        return new LoginResponseProprietarioDTO(
            "mockToken",
            new Date()
        );
    }
}
