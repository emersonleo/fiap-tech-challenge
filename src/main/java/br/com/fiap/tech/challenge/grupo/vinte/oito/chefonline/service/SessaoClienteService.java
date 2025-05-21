package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

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
                .orElseThrow(() -> new RuntimeException("Informacoes incorretas de login"));

        if (!senhaService.verificaSenha(senha, cliente.getUsuario().getSenha())) {
            throw new RuntimeException("Informacoes incorretas de login");
        }
    }
}
