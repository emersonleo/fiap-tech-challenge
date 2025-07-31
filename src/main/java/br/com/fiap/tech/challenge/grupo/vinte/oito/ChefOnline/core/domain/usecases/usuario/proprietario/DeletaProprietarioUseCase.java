package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;

public class DeletaProprietarioUseCase {
    final IProprietarioGateway proprietarioGateway;

    private DeletaProprietarioUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static DeletaProprietarioUseCase create(IProprietarioGateway proprietarioGateway) {
        return new DeletaProprietarioUseCase(proprietarioGateway);
    }

    public void run(Long id) {
        final Proprietario proprietarioExistente = proprietarioGateway.buscaProprietarioPorId(id)
                .orElseThrow(() -> new ProprietarioNotFoundException(id));

        proprietarioGateway.deletaProprietario(proprietarioExistente);
    }
}