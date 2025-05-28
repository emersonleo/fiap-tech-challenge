package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services;

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
                .orElseThrow(() -> new RuntimeException("Informações incorretas de login"));

        if (!senhaService.verificaSenha(senha, proprietario.getUsuario().getSenha())) {
            throw new RuntimeException("Informações incorretas de login");
        }
    }
}
