package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.EmailJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.LoginJaCadastrado;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioGateway;

import java.util.Optional;

public class CriaClienteUseCase {
    final IClienteGateway clienteGateway;
    final IUsuarioGateway usuarioGateway;

    private CriaClienteUseCase(IClienteGateway clienteGateway, IUsuarioGateway usuarioGateway) {
        this.clienteGateway = clienteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public static CriaClienteUseCase create(IClienteGateway clienteGateway, IUsuarioGateway usuarioGateway) {
        return new CriaClienteUseCase(clienteGateway, usuarioGateway);
    }

    public Cliente run(NovoClienteDTO novoClienteDTO) {
        this.assertEmailUnico(novoClienteDTO.email());
        this.assertLoginUnico(novoClienteDTO.login());

        final Cliente novoCliente = new Cliente(
                novoClienteDTO.id(),
                novoClienteDTO.nome(),
                novoClienteDTO.email(),
                novoClienteDTO.login(),
                novoClienteDTO.senha(),
                novoClienteDTO.endereco()
        );

        return clienteGateway.adicionaCliente(novoCliente);
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
