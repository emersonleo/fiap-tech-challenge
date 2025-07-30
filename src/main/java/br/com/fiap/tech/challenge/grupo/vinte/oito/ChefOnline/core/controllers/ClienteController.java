package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.CriaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.BuscaTodosClientesUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.BuscaClientePorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.BuscaClientePorLoginUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.AtualizaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.DeletaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente.AtualizaSenhaClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.ClienteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.ClientePresenter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClienteController {
    IClienteDataSource clienteDataSource;

    public ClienteController(IClienteDataSource clienteDataSource) {
        this.clienteDataSource = clienteDataSource;
    }

    public ClienteDTO criaCliente(NovoClienteDTO novoClienteDTO) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var criaCliente = CriaClienteUseCase.create(gateway);

        try {
            var cliente = criaCliente.run(novoClienteDTO);
            return ClientePresenter.toDTO(cliente);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

    public List<ClienteDTO> buscaTodosClientes(int page, int size) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var buscaTodosClientes = BuscaTodosClientesUseCase.create(gateway);

        try {
            var clientes = buscaTodosClientes.run(page, size);
            return clientes.stream()
                    .map(ClientePresenter::toDTO)
                    .toList();
        } catch (Exception e) {
            // ...
            return Collections.emptyList();
        }
    }

    public ClienteDTO buscaClientePorId(Long id) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var buscaClientePorId = BuscaClientePorIdUseCase.create(gateway);

        try {
            var cliente = buscaClientePorId.run(id);
            return ClientePresenter.toDTO(cliente);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

    public Optional<ClienteDTO> buscaClientePorLogin(String login) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var buscaClientePorLogin = BuscaClientePorLoginUseCase.create(gateway);

        try {
            var clienteOpt = buscaClientePorLogin.run(login);
            return clienteOpt.map(ClientePresenter::toDTO);
        } catch (Exception e) {
            // ...
            return Optional.empty();
        }
    }

    public ClienteDTO atualizaCliente(AtualizaClienteDTO atualizaClienteDTO, Long id) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var atualizaCliente = AtualizaClienteUseCase.create(gateway);

        try {
            var cliente = atualizaCliente.run(atualizaClienteDTO, id);
            return ClientePresenter.toDTO(cliente);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

    public void deletaCliente(Long id) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var deletaCliente = DeletaClienteUseCase.create(gateway);

        try {
            deletaCliente.run(id);
        } catch (Exception e) {
            // ...
        }
    }

    public ClienteDTO atualizaSenha(TrocaSenhaDTO trocaSenhaDTO) {
        var gateway = ClienteGateway.create(clienteDataSource);
        var atualizaSenha = AtualizaSenhaClienteUseCase.create(gateway);

        try {
            var cliente = atualizaSenha.run(trocaSenhaDTO);
            return ClientePresenter.toDTO(cliente);
        } catch (Exception e) {
            // ...
            return null;
        }
    }

}
