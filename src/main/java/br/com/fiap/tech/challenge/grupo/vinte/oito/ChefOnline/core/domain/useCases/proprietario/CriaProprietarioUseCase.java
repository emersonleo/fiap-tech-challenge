package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario.ProprietarioJaExisteException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioGateway;

public class CriaProprietarioUseCase {
    final IProprietarioGateway proprietarioGateway;

    private CriaProprietarioUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static CriaProprietarioUseCase create(IProprietarioGateway proprietarioGateway) {
        return new CriaProprietarioUseCase(proprietarioGateway);
    }

    public Proprietario run(NovoProprietarioDTO novoProprietarioDTO) {
        final Proprietario checkProprietario = proprietarioGateway.buscaProprietarioPorEmail(novoProprietarioDTO.email());

        if (checkProprietario != null) {
            throw new ProprietarioJaExisteException(novoProprietarioDTO.email());
        }

        final Proprietario novoProprietario = new Proprietario(
                novoProprietarioDTO.id(),
                novoProprietarioDTO.nome(),
                novoProprietarioDTO.email(),
                novoProprietarioDTO.login(),
                novoProprietarioDTO.senha(),
                novoProprietarioDTO.endereco()
        );

        return proprietarioGateway.adicionaProprietario(novoProprietario);
    }
}