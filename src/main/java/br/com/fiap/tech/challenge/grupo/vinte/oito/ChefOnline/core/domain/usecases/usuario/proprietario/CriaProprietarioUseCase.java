package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.EmailJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.LoginJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioGateway;

import java.util.Optional;

public class CriaProprietarioUseCase {
    final IProprietarioGateway proprietarioGateway;
    final IUsuarioGateway usuarioGateway;

    private CriaProprietarioUseCase(IProprietarioGateway proprietarioGateway, IUsuarioGateway usuarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public static CriaProprietarioUseCase create(IProprietarioGateway proprietarioGateway, IUsuarioGateway usuarioGateway) {
        return new CriaProprietarioUseCase(proprietarioGateway, usuarioGateway);
    }

    public Proprietario run(NovoProprietarioDTO novoProprietarioDTO) {
        this.assertEmailUnico(novoProprietarioDTO.email());
        this.assertLoginUnico(novoProprietarioDTO.login());

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

    private void assertEmailUnico(String email) {
        final Optional<Usuario> checkUsuario = usuarioGateway.buscaUsuarioPorEmail(email);
        checkUsuario.ifPresent(usuarioExistente -> {
            throw new EmailJaCadastrado(usuarioExistente.getEmail(), usuarioExistente.getTipo());
        });
    }

    private void assertLoginUnico(String login) {
        final Optional<Usuario> checkUsuario = usuarioGateway.buscaUsuarioPorLogin(login);
        checkUsuario.ifPresent(usuarioExistente -> {
            throw new LoginJaCadastrado(usuarioExistente.getLogin(), usuarioExistente.getTipo());
        });
    }
}