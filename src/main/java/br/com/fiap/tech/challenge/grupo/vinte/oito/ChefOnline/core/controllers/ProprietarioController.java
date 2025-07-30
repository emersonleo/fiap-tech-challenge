package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.CriaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.BuscaTodosProprietariosUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.BuscaProprietarioPorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.BuscaProprietarioPorLoginUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.AtualizaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.DeletaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.proprietario.AtualizaSenhaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.ProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.ProprietarioPresenter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProprietarioController {
    IProprietarioDataSource proprietarioDataSource;

    public ProprietarioController(IProprietarioDataSource proprietarioDataSource) {
        this.proprietarioDataSource = proprietarioDataSource;
    }

    public ProprietarioDTO criaProprietario(NovoProprietarioDTO novoProprietarioDTO) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var criaProprietario = CriaProprietarioUseCase.create(gateway);

        try {
            var proprietario = criaProprietario.run(novoProprietarioDTO);
            return ProprietarioPresenter.toDTO(proprietario);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

    public List<ProprietarioDTO> buscaTodosProprietarios(int page, int size) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaTodosProprietarios = BuscaTodosProprietariosUseCase.create(gateway);

        try {
            var proprietarios = buscaTodosProprietarios.run(page, size);
            return proprietarios.stream()
                    .map(ProprietarioPresenter::toDTO)
                    .toList();
        } catch (Exception e) {
            // ...
            return Collections.emptyList();
        }
    }

    public ProprietarioDTO buscaProprietarioPorId(Long id) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaProprietarioPorId = BuscaProprietarioPorIdUseCase.create(gateway);

        try {
            var proprietario = buscaProprietarioPorId.run(id);
            return ProprietarioPresenter.toDTO(proprietario);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

    public Optional<ProprietarioDTO> buscaProprietarioPorLogin(String login) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaProprietarioPorLogin = BuscaProprietarioPorLoginUseCase.create(gateway);

        try {
            var proprietarioOpt = buscaProprietarioPorLogin.run(login);
            return proprietarioOpt.map(ProprietarioPresenter::toDTO);
        } catch (Exception e) {
            // ...
            return Optional.empty();
        }
    }

    public ProprietarioDTO atualizaProprietario(AtualizaProprietarioDTO atualizaProprietarioDTO, Long id) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var atualizaProprietario = AtualizaProprietarioUseCase.create(gateway);

        try {
            var proprietario = atualizaProprietario.run(atualizaProprietarioDTO, id);
            return ProprietarioPresenter.toDTO(proprietario);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

    public void deletaProprietario(Long id) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var deletaProprietario = DeletaProprietarioUseCase.create(gateway);

        try {
            deletaProprietario.run(id);
        } catch (Exception e) {
            // ...
        }
    }

    public ProprietarioDTO atualizaSenha(TrocaSenhaDTO trocaSenhaDTO) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var atualizaSenha = AtualizaSenhaProprietarioUseCase.create(gateway);

        try {
            var proprietario = atualizaSenha.run(trocaSenhaDTO);
            return ProprietarioPresenter.toDTO(proprietario);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

}