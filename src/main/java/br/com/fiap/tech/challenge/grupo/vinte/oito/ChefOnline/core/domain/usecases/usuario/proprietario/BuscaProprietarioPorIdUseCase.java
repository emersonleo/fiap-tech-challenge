package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;

public class BuscaProprietarioPorIdUseCase {
    final IProprietarioGateway proprietarioGateway;

    private BuscaProprietarioPorIdUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static BuscaProprietarioPorIdUseCase create(IProprietarioGateway proprietarioGateway) {
        return new BuscaProprietarioPorIdUseCase(proprietarioGateway);
    }

    public Proprietario run(Long id) {
        return proprietarioGateway.buscaProprietarioPorId(id)
                .orElseThrow(() -> ProprietarioNotFoundException.withId(id));
    }
}