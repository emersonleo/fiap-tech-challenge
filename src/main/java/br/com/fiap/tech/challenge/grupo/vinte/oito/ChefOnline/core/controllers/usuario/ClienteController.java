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
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.ClientePresenter;

import java.util.List;
import java.util.Optional;

public class ClienteController {
    private final IClienteDataSource clienteDataSource;

    public ClienteController(IClienteDataSource clienteDataSource) {
        this.clienteDataSource = clienteDataSource;
    }

    public ClienteDTO criaCliente(NovoClienteDTO novoClienteDTO) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var criaCliente = CriaClienteUseCase.create(gateway);

        var cliente = criaCliente.run(novoClienteDTO);
        return ClientePresenter.toDTO(cliente);
    }

    public List<ClienteDTO> buscaTodosClientes(int page, int size) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var buscaTodosClientes = BuscaTodosClientesUseCase.create(gateway);

        var clientes = buscaTodosClientes.run(page, size);
        return clientes.stream()
                .map(ClientePresenter::toDTO)
                .toList();
    }

    public ClienteDTO buscaClientePorId(Long id) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var buscaClientePorId = BuscaClientePorIdUseCase.create(gateway);

        var cliente = buscaClientePorId.run(id);
        return ClientePresenter.toDTO(cliente);
    }

    public Optional<ClienteDTO> buscaClientePorLogin(String login) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var buscaClientePorLogin = BuscaClientePorLoginUseCase.create(gateway);

        var clienteOpt = buscaClientePorLogin.run(login);
        return clienteOpt.map(ClientePresenter::toDTO);
    }

    public ClienteDTO atualizaCliente(AtualizaClienteDTO atualizaClienteDTO, Long id) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var atualizaCliente = AtualizaClienteUseCase.create(gateway);

        var cliente = atualizaCliente.run(atualizaClienteDTO, id);
        return ClientePresenter.toDTO(cliente);
    }

    public void deletaCliente(Long id) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var deletaCliente = DeletaClienteUseCase.create(gateway);

        deletaCliente.run(id);
    }

    public ClienteDTO atualizaSenha(TrocaSenhaDTO trocaSenhaDTO) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var atualizaSenha = AtualizaSenhaClienteUseCase.create(gateway);

        var cliente = atualizaSenha.run(trocaSenhaDTO);
        return ClientePresenter.toDTO(cliente);
    }

}
