package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioGateway;

import java.util.List;

public class BuscaTodosProprietariosUseCase {
    final IProprietarioGateway proprietarioGateway;

    private BuscaTodosProprietariosUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static BuscaTodosProprietariosUseCase create(IProprietarioGateway proprietarioGateway) {
        return new BuscaTodosProprietariosUseCase(proprietarioGateway);
    }

    public List<Proprietario> run(int page, int size) {
        return proprietarioGateway.buscaTodosProprietarios(page, size);
    }
}