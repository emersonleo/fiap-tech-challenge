package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.CriaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.BuscaTodosProprietariosUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.BuscaProprietarioPorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.BuscaProprietarioPorLoginUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.AtualizaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.DeletaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario.AtualizaSenhaProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.ProprietarioPresenter;

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

        var proprietario = criaProprietario.run(novoProprietarioDTO);
        return ProprietarioPresenter.toDTO(proprietario);
    }

    public List<ProprietarioDTO> buscaTodosProprietarios(int page, int size) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaTodosProprietarios = BuscaTodosProprietariosUseCase.create(gateway);

        var proprietarios = buscaTodosProprietarios.run(page, size);
        return proprietarios.stream()
                .map(ProprietarioPresenter::toDTO)
                .toList();
    }

    public ProprietarioDTO buscaProprietarioPorId(Long id) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaProprietarioPorId = BuscaProprietarioPorIdUseCase.create(gateway);

        var proprietario = buscaProprietarioPorId.run(id);
        return ProprietarioPresenter.toDTO(proprietario);
    }

    public Optional<ProprietarioDTO> buscaProprietarioPorLogin(String login) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaProprietarioPorLogin = BuscaProprietarioPorLoginUseCase.create(gateway);

        var proprietarioOpt = buscaProprietarioPorLogin.run(login);
        return proprietarioOpt.map(ProprietarioPresenter::toDTO);
    }

    public ProprietarioDTO atualizaProprietario(AtualizaProprietarioDTO atualizaProprietarioDTO, Long id) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var atualizaProprietario = AtualizaProprietarioUseCase.create(gateway);

        var proprietario = atualizaProprietario.run(atualizaProprietarioDTO, id);
        return ProprietarioPresenter.toDTO(proprietario);
    }

    public void deletaProprietario(Long id) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var deletaProprietario = DeletaProprietarioUseCase.create(gateway);

        deletaProprietario.run(id);
    }

    public ProprietarioDTO atualizaSenha(TrocaSenhaDTO trocaSenhaDTO) {
        var gateway = ProprietarioGateway.create(proprietarioDataSource);
        var atualizaSenha = AtualizaSenhaProprietarioUseCase.create(gateway);

        var proprietario = atualizaSenha.run(trocaSenhaDTO);
        return ProprietarioPresenter.toDTO(proprietario);
    }

}