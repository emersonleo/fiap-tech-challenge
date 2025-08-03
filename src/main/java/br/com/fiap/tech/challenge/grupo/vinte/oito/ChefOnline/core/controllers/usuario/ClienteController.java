package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.CriaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.BuscaTodosClientesUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.BuscaClientePorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.BuscaClientePorLoginUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.AtualizaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.DeletaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.AtualizaSenhaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ClienteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.UsuarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.ClientePresenter;

import java.util.List;
import java.util.Optional;

public class ClienteController {
    private final IClienteDataSource clienteDataSource;
    private final IUsuarioDataSource usuarioDataSource;

    public ClienteController(IClienteDataSource clienteDataSource, IUsuarioDataSource usuarioDataSource) {
        this.clienteDataSource = clienteDataSource;
        this.usuarioDataSource = usuarioDataSource;
    }

    public ClienteDTO criaCliente(NovoClienteDTO novoClienteDTO) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var usuarioGateway = UsuarioGateway.create(usuarioDataSource);
        var criaCliente = CriaClienteUseCase.create(clienteGateway, usuarioGateway);

        var cliente = criaCliente.run(novoClienteDTO);
        return ClientePresenter.toDTO(cliente);
    }

    public List<ClienteDTO> buscaTodosClientes(int page, int size) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var buscaTodosClientes = BuscaTodosClientesUseCase.create(clienteGateway);

        var clientes = buscaTodosClientes.run(page, size);
        return clientes.stream()
                .map(ClientePresenter::toDTO)
                .toList();
    }

    public ClienteDTO buscaClientePorId(Long id) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var buscaClientePorId = BuscaClientePorIdUseCase.create(clienteGateway);

        var cliente = buscaClientePorId.run(id);
        return ClientePresenter.toDTO(cliente);
    }

    public ClienteDTO buscaClientePorLogin(String login) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var buscaClientePorLogin = BuscaClientePorLoginUseCase.create(clienteGateway);

        var cliente = buscaClientePorLogin.run(login);
        return ClientePresenter.toDTO(cliente);
    }

    public ClienteDTO atualizaCliente(AtualizaClienteDTO atualizaClienteDTO, Long id) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var atualizaCliente = AtualizaClienteUseCase.create(clienteGateway);

        var cliente = atualizaCliente.run(atualizaClienteDTO, id);
        return ClientePresenter.toDTO(cliente);
    }

    public void deletaCliente(Long id) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var deletaCliente = DeletaClienteUseCase.create(clienteGateway);

        deletaCliente.run(id);
    }

    public ClienteDTO atualizaSenha(TrocaSenhaDTO trocaSenhaDTO) {
        var clienteGateway = ClienteGateway.create(clienteDataSource);
        var atualizaSenha = AtualizaSenhaClienteUseCase.create(clienteGateway);

        var cliente = atualizaSenha.run(trocaSenhaDTO);
        return ClientePresenter.toDTO(cliente);
    }

}
