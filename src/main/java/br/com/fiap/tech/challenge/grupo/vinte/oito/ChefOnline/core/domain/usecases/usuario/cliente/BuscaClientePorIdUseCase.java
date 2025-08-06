package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

public class BuscaClientePorIdUseCase {
    final IClienteGateway clienteGateway;

    private BuscaClientePorIdUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static BuscaClientePorIdUseCase create(IClienteGateway clienteGateway) {
        return new BuscaClientePorIdUseCase(clienteGateway);
    }

    public Cliente run(Long id) {
        return clienteGateway.buscaClientePorId(id)
                .orElseThrow(() -> ClienteNotFoundException.withId(id));
    }
}
