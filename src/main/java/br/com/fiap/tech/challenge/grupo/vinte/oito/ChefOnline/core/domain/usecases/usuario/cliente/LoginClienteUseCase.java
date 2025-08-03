package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.LoginResponseClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.auth.InvalidAuthException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthGateway;

import java.util.Date;

public class LoginClienteUseCase {
    private final IClienteAuthGateway clienteAuthGateway;

    private LoginClienteUseCase(IClienteAuthGateway clienteAuthGateway) {
        this.clienteAuthGateway = clienteAuthGateway;
    }

    public static LoginClienteUseCase create(IClienteAuthGateway clienteAuthGateway) {
        return new LoginClienteUseCase(clienteAuthGateway);
    }

    public LoginResponseClienteDTO run(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        if (!this.clienteAuthGateway.verificaCredenciais(verificaCredenciaisDTO)) {
            throw new InvalidAuthException(NomeDoTipo.CLIENTE);
        }

        return new LoginResponseClienteDTO(
                "mockToken",
                new Date()
        );
    }
}
