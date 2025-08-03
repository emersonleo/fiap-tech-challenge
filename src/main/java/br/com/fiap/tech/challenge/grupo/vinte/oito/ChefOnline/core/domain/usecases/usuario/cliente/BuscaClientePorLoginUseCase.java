package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

public class BuscaClientePorLoginUseCase {
    final IClienteGateway clienteGateway;

    private BuscaClientePorLoginUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static BuscaClientePorLoginUseCase create(IClienteGateway clienteGateway) {
        return new BuscaClientePorLoginUseCase(clienteGateway);
    }

    public Cliente run(String login) {
        return clienteGateway.buscaClientePorLogin(login).orElseThrow(() -> ClienteNotFoundException.withLogin(login));
    }
}
