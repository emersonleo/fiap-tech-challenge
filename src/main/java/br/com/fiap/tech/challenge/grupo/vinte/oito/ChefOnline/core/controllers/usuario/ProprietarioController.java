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
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.UsuarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.ProprietarioPresenter;

import java.util.List;
import java.util.Optional;

public class ProprietarioController {
    private final IProprietarioDataSource proprietarioDataSource;
    private final IUsuarioDataSource usuarioDataSource;

    public ProprietarioController(IProprietarioDataSource proprietarioDataSource, IUsuarioDataSource usuarioDataSource) {
        this.proprietarioDataSource = proprietarioDataSource;
        this.usuarioDataSource = usuarioDataSource;
    }

    public ProprietarioDTO criaProprietario(NovoProprietarioDTO novoProprietarioDTO) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var usuarioGateway = UsuarioGateway.create(usuarioDataSource);
        var criaProprietario = CriaProprietarioUseCase.create(proprietarioGateway, usuarioGateway);

        var proprietario = criaProprietario.run(novoProprietarioDTO);
        return ProprietarioPresenter.toDTO(proprietario);
    }

    public List<ProprietarioDTO> buscaTodosProprietarios(int page, int size) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaTodosProprietarios = BuscaTodosProprietariosUseCase.create(proprietarioGateway);

        var proprietarios = buscaTodosProprietarios.run(page, size);
        return proprietarios.stream()
                .map(ProprietarioPresenter::toDTO)
                .toList();
    }

    public ProprietarioDTO buscaProprietarioPorId(Long id) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaProprietarioPorId = BuscaProprietarioPorIdUseCase.create(proprietarioGateway);

        var proprietario = buscaProprietarioPorId.run(id);
        return ProprietarioPresenter.toDTO(proprietario);
    }

    public Optional<ProprietarioDTO> buscaProprietarioPorLogin(String login) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var buscaProprietarioPorLogin = BuscaProprietarioPorLoginUseCase.create(proprietarioGateway);

        var proprietarioOpt = buscaProprietarioPorLogin.run(login);
        return proprietarioOpt.map(ProprietarioPresenter::toDTO);
    }

    public ProprietarioDTO atualizaProprietario(AtualizaProprietarioDTO atualizaProprietarioDTO, Long id) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var atualizaProprietario = AtualizaProprietarioUseCase.create(proprietarioGateway);

        var proprietario = atualizaProprietario.run(atualizaProprietarioDTO, id);
        return ProprietarioPresenter.toDTO(proprietario);
    }

    public void deletaProprietario(Long id) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var deletaProprietario = DeletaProprietarioUseCase.create(proprietarioGateway);

        deletaProprietario.run(id);
    }

    public ProprietarioDTO atualizaSenha(TrocaSenhaDTO trocaSenhaDTO) {
        var proprietarioGateway = ProprietarioGateway.create(proprietarioDataSource);
        var atualizaSenha = AtualizaSenhaProprietarioUseCase.create(proprietarioGateway);

        var proprietario = atualizaSenha.run(trocaSenhaDTO);
        return ProprietarioPresenter.toDTO(proprietario);
    }

}