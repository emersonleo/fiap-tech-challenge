package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain;

public class Proprietario extends Usuario {
    private String cnpj;

    public Proprietario(String nome, String email, String login, String senha, String endereco, String cnpj) {
        super(nome, email, login, senha, endereco);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
}
