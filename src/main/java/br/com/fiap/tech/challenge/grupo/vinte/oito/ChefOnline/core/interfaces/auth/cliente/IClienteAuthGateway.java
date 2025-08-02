package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.LoginResponseClienteDTO;

public interface IClienteAuthGateway {
    boolean verificaCredenciais(VerificaCredenciaisDTO verificaCredenciaisDTO);
}
