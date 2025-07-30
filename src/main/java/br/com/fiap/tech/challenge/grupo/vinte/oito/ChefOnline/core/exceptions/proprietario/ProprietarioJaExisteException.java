package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario;

public class ProprietarioJaExisteException extends RuntimeException {

    private final String email;

    public ProprietarioJaExisteException(String email) {
        super("Proprietário já cadastrado com o email: " + email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}