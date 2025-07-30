package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario;

public class ProprietarioNotFoundException extends RuntimeException {

    private final Long id;

    public ProprietarioNotFoundException(String message) {
        super(message);
        this.id = null;
    }

    public ProprietarioNotFoundException(Long id) {
        super("Proprietário não encontrado com o id: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}