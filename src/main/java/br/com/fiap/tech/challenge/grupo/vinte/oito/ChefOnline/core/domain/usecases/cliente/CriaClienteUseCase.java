package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente.ClienteJaExisteException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IClienteGateway;

public class CriaClienteUseCase {
    final IClienteGateway clienteGateway;

    private CriaClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static CriaClienteUseCase create(IClienteGateway clienteGateway) {
        return new CriaClienteUseCase(clienteGateway);
    }

    public Cliente run(NovoClienteDTO novoClienteDTO) {
        final Cliente checkCliente = clienteGateway.buscaClientePorEmail(novoClienteDTO.email());

        if (checkCliente != null) {
            throw new ClienteJaExisteException(novoClienteDTO.email());
        }

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
}
