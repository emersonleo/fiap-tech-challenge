package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain;

public class Cliente extends Usuario {
    private String cpf;

    public Cliente(String nome, String email, String login, String senha, String endereco, String cpf) {
        super(nome, email, login, senha, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
