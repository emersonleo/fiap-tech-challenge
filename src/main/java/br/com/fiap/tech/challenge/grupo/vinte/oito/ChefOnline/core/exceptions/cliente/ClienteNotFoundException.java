package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente;

public class ClienteNotFoundException extends RuntimeException {

    private final Long id;

    public ClienteNotFoundException(String message) {
        super(message);
        this.id = null;
    }

    public ClienteNotFoundException(Long id) {
        super("Cliente não encontrado com o id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
