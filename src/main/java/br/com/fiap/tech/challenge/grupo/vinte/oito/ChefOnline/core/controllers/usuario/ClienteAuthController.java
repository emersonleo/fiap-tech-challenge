package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente.LoginClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.LoginResponseClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.auth.cliente.ClienteAuthGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthDataSource;

public class ClienteAuthController {
    private final IClienteAuthDataSource clienteAuthDataSource;

    public ClienteAuthController(IClienteAuthDataSource clienteAuthDataSource) {
        this.clienteAuthDataSource = clienteAuthDataSource;
    }

    public LoginResponseClienteDTO login(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        var gateway = ClienteAuthGateway.create(clienteAuthDataSource);
        var loginUseCase = LoginClienteUseCase.create(gateway);
        return loginUseCase.run(verificaCredenciaisDTO);
    }
}
