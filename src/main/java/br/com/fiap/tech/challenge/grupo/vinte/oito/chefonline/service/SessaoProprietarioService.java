package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import org.springframework.stereotype.Service;

@Service
public class SessaoProprietarioService {
    ProprietarioService proprietarioService;
    SenhaService senhaService;

    SessaoProprietarioService(ProprietarioService proprietarioService, SenhaService senhaService) {
        this.proprietarioService = proprietarioService;
        this.senhaService = senhaService;
    }

    public void login(String login, String senha) {
        var proprietario = proprietarioService
                .buscaProprietarioPorLogin(login)
                .orElseThrow(() -> new RuntimeException("Informacoes incorretas de login"));

        if (!senhaService.verificaSenha(senha, proprietario.getSenha())) {
            throw new RuntimeException("Informacoes incorretas de login");
        }
    }
}
