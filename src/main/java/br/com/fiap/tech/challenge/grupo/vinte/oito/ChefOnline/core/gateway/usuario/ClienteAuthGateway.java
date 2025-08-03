package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthGateway;

public class ClienteAuthGateway implements IClienteAuthGateway {
    private final IClienteAuthDataSource clienteAuthDataSource;

    private ClienteAuthGateway(IClienteAuthDataSource clienteAuthDataSource){
        this.clienteAuthDataSource = clienteAuthDataSource;
    }

    public static ClienteAuthGateway create(IClienteAuthDataSource clienteAuthDataSource) {
        return new ClienteAuthGateway(clienteAuthDataSource);
    }

    @Override
    public boolean verificaCredenciais(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        return clienteAuthDataSource.verificaCredenciais(verificaCredenciaisDTO);
    }
}
