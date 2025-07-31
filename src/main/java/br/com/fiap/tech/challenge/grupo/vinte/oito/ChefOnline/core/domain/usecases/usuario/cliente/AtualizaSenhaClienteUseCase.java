package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
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
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado com login: " + trocaSenhaDTO.login()));

        // Verificar se a senha atual está correta
        if (!clienteExistente.getSenha().equals(trocaSenhaDTO.senhaAtual())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        // Atualizar senha
        clienteExistente.setSenha(trocaSenhaDTO.novaSenha());

        return clienteGateway.atualizaCliente(clienteExistente);
    }
}