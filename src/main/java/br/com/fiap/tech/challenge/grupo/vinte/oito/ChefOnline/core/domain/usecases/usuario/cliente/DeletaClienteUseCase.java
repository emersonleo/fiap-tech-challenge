package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

public class DeletaClienteUseCase {
    final IClienteGateway clienteGateway;

    private DeletaClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static DeletaClienteUseCase create(IClienteGateway clienteGateway) {
        return new DeletaClienteUseCase(clienteGateway);
    }

    public void run(Long id) {
        final Cliente clienteExistente = clienteGateway.buscaClientePorId(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

        clienteGateway.deletaCliente(clienteExistente);
    }
}
