package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.AtualizaClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

public class AtualizaClienteUseCase {
    final IClienteGateway clienteGateway;

    private AtualizaClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static AtualizaClienteUseCase create(IClienteGateway clienteGateway) {
        return new AtualizaClienteUseCase(clienteGateway);
    }

    public Cliente run(AtualizaClienteDTO clienteRequestDTO, Long id) {
        final Cliente clienteExistente = clienteGateway.buscaClientePorId(id)
                .orElseThrow(() -> ClienteNotFoundException.withId(id));

        clienteExistente.setNome(clienteRequestDTO.nome());
        clienteExistente.setEmail(clienteRequestDTO.email());
        clienteExistente.setLogin(clienteRequestDTO.login());
        clienteExistente.setEndereco(clienteRequestDTO.endereco());

        return clienteGateway.atualizaCliente(clienteExistente);
    }

}
