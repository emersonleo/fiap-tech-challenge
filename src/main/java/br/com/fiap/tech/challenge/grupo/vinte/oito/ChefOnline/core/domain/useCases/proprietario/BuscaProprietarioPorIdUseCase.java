package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioGateway;

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
                .orElseThrow(() -> new ProprietarioNotFoundException(id));
    }
}