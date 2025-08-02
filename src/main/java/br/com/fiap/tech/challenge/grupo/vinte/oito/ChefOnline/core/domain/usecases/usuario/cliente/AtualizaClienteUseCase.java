package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.InternalServerException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioGateway;

import java.util.Date;

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
                .orElseThrow(() -> new ClienteNotFoundException(id));

        clienteExistente.setNome(clienteRequestDTO.nome());
        clienteExistente.setEmail(clienteRequestDTO.email());
        clienteExistente.setLogin(clienteRequestDTO.login());
        clienteExistente.setSenha(clienteRequestDTO.senha());
        clienteExistente.setEndereco(clienteRequestDTO.endereco());
        clienteExistente.setDataUltimaAlteracao(new Date());

        try {
            return clienteGateway.atualizaCliente(clienteExistente);
        } catch (ClienteNotFoundException e) {
            throw new InternalServerException(
                "Erro interno: Cliente desapareceu durante atualização (possível race condition)", e);
        }
    }
    
}
