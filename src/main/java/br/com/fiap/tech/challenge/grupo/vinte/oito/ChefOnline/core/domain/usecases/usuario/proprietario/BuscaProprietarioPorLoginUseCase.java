package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;

public class BuscaProprietarioPorLoginUseCase {
    final IProprietarioGateway proprietarioGateway;

    private BuscaProprietarioPorLoginUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static BuscaProprietarioPorLoginUseCase create(IProprietarioGateway proprietarioGateway) {
        return new BuscaProprietarioPorLoginUseCase(proprietarioGateway);
    }

    public Proprietario run(String login) {
        return proprietarioGateway.buscaProprietarioPorLogin(login)
                .orElseThrow(() -> ProprietarioNotFoundException.withLogin(login));
    }
}