package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente;

public class ClienteJaExisteException extends RuntimeException {

    private final String email;

    public ClienteJaExisteException(String email) {
        super("Cliente já cadastrado com o email: " + email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
