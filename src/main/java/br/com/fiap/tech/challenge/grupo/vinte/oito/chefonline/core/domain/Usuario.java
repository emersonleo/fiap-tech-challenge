package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain;

import java.time.LocalDateTime;

public class Usuario {
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private LocalDateTime dataUltimaAlteracaoRegistro;
    private LocalDateTime dataCriacaoRegistro;

    protected Usuario(String nome, String email, String login, String senha, String endereco) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.dataCriacaoRegistro = LocalDateTime.now();
        this.dataUltimaAlteracaoRegistro = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDateTime getDataUltimaAlteracaoRegistro() {
        return dataUltimaAlteracaoRegistro;
    }

    public LocalDateTime getDataCriacaoRegistro() {
        return dataCriacaoRegistro;
    }

    protected void atualizarInformacoes(String nome, String email, String endereco) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.dataUltimaAlteracaoRegistro = LocalDateTime.now();
    }
}
