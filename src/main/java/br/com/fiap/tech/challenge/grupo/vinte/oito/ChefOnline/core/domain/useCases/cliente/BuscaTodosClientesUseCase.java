package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.useCases.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IClienteGateway;

import java.util.List;

public class BuscaTodosClientesUseCase {
    final IClienteGateway clienteGateway;

    private BuscaTodosClientesUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static BuscaTodosClientesUseCase create(IClienteGateway clienteGateway) {
        return new BuscaTodosClientesUseCase(clienteGateway);
    }

    public List<Cliente> run(int page, int size) {
        return clienteGateway.buscaTodosClientes(page, size);
    }
}
