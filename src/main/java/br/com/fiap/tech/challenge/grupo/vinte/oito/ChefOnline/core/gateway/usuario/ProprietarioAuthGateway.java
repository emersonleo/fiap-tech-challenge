package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.proprietario.IProprietarioAuthDataSource;

public class ProprietarioAuthGateway {
    private final IProprietarioAuthDataSource proprietarioAuthDataSource;

    private ProprietarioAuthGateway(IProprietarioAuthDataSource proprietarioAuthDataSource) {
        this.proprietarioAuthDataSource = proprietarioAuthDataSource;
    }

    public static ProprietarioAuthGateway create(IProprietarioAuthDataSource proprietarioAuthDataSource) {
        return new ProprietarioAuthGateway(proprietarioAuthDataSource);
    }

    public boolean verificaCredenciais(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        return proprietarioAuthDataSource.verificaCredenciais(verificaCredenciaisDTO);
    }
}
