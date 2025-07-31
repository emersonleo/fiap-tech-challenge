package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

import java.util.List;
import java.util.Optional;

public class ClienteGateway implements IClienteGateway {
    private final IClienteDataSource clienteDataSource;

    private ClienteGateway(IClienteDataSource clienteDataSource) {
        this.clienteDataSource = clienteDataSource;
    }

    public static ClienteGateway create(IClienteDataSource clienteDataSource) {
       return new ClienteGateway(clienteDataSource);
    }

    @Override
    public Cliente adicionaCliente(Cliente novoCliente) {
        return clienteDataSource.adicionaCliente(novoCliente);
    }

    @Override
    public Cliente buscaClientePorEmail(String email) {
        return clienteDataSource.buscaClientePorEmail(email);
    }

    @Override
    public Optional<Cliente> buscaClientePorId(Long id) {
        return clienteDataSource.buscaClientePorId(id);
    }

    @Override
    public List<Cliente> buscaTodosClientes(int page, int size) {
        return clienteDataSource.buscaTodosClientes(page, size);
    }

    @Override
    public Cliente atualizaCliente(Cliente cliente) {
        return clienteDataSource.atualizaCliente(cliente);
    }

    @Override
    public Optional<Cliente> buscaClientePorLogin(String login) {
        return clienteDataSource.buscaClientePorLogin(login);
    }

    @Override
    public void deletaCliente(Cliente cliente) {
        clienteDataSource.deletaCliente(cliente);
    }
}
