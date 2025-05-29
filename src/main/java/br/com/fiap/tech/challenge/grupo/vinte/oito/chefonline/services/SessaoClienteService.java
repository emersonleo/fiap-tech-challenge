package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.exceptions.FalhaLoginException;
import org.springframework.stereotype.Service;

@Service
public class SessaoClienteService {
    ClienteService clienteService;
    SenhaService senhaService;

    SessaoClienteService(ClienteService clienteService, SenhaService senhaService) {
        this.clienteService = clienteService;
        this.senhaService = senhaService;
    }

    public void login(String login, String senha) {
        var cliente = clienteService
                .buscaClientPorLogin(login)
                .orElseThrow(() -> new FalhaLoginException("Ops! Falha ao realizar login. Verifique as informações fornecidas e tente novamente."));

        if (!senhaService.verificaSenha(senha, cliente.getUsuario().getSenha())) {
            throw new FalhaLoginException("Ops! Falha ao realizar login. Verifique as informações fornecidas e tente novamente.");
        }
    }
}
