package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.exceptions.FalhaLoginException;
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
                .orElseThrow(() -> new FalhaLoginException("Ops! Falha ao realizar login. Verifique as informações fornecidas e tente novamente."));

        if (!senhaService.verificaSenha(senha, proprietario.getUsuario().getSenha())) {
            throw new FalhaLoginException("Ops! Falha ao realizar login. Verifique as informações fornecidas e tente novamente.");
        }
    }
}
