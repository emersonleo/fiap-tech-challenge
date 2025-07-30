package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioGateway;

import java.util.Date;

public class AtualizaProprietarioUseCase {
    final IProprietarioGateway proprietarioGateway;

    private AtualizaProprietarioUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static AtualizaProprietarioUseCase create(IProprietarioGateway proprietarioGateway) {
        return new AtualizaProprietarioUseCase(proprietarioGateway);
    }

    public Proprietario run(AtualizaProprietarioDTO proprietarioRequestDTO, Long id) {
        final Proprietario proprietarioExistente = proprietarioGateway.buscaProprietarioPorId(id)
                .orElseThrow(() -> new ProprietarioNotFoundException(id));

        proprietarioExistente.setNome(proprietarioRequestDTO.nome());
        proprietarioExistente.setEmail(proprietarioRequestDTO.email());
        proprietarioExistente.setLogin(proprietarioRequestDTO.login());
        proprietarioExistente.setSenha(proprietarioRequestDTO.senha());
        proprietarioExistente.setEndereco(proprietarioRequestDTO.endereco());
        proprietarioExistente.setDataUltimaAlteracao(new Date());

        return proprietarioGateway.atualizaProprietario(proprietarioExistente);
    }
}