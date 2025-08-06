package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.SenhaIncorretaException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

public class AtualizaSenhaClienteUseCase {
    final IClienteGateway clienteGateway;

    private AtualizaSenhaClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static AtualizaSenhaClienteUseCase create(IClienteGateway clienteGateway) {
        return new AtualizaSenhaClienteUseCase(clienteGateway);
    }

    public Cliente run(TrocaSenhaDTO trocaSenhaDTO) {
        final Cliente clienteExistente = clienteGateway.buscaClientePorLogin(trocaSenhaDTO.login())
                .orElseThrow(() -> ClienteNotFoundException.withLogin(trocaSenhaDTO.login()));

        if (!clienteExistente.getSenha().equals(trocaSenhaDTO.senhaAtual())) {
            throw new SenhaIncorretaException("Senha atual incorreta");
        }

        clienteExistente.setSenha(trocaSenhaDTO.novaSenha());

        return clienteGateway.atualizaCliente(clienteExistente);
    }
}