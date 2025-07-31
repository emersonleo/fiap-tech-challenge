package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.ProprietarioRepositoryPort;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.UsuarioRepositoryPort;

public class CriarProprietarioUseCase {
    private final ProprietarioRepositoryPort proprietarioRepository;
    private final UsuarioRepositoryPort usuarioRepository;

    public CriarProprietarioUseCase(ProprietarioRepositoryPort proprietarioRepository, UsuarioRepositoryPort usuarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Proprietario executar(String nome, String email, String login, String senha, String endereco, String cnpj) {
        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (usuarioRepository.buscarPorLogin(login).isPresent()) {
            throw new IllegalArgumentException("Login já cadastrado");
        }
        if (proprietarioRepository.existePorCnpj(cnpj)) {
            throw new IllegalArgumentException("CNPJ já cadastrado");
        }

        Proprietario proprietario = new Proprietario(nome, email, login, senha, endereco, cnpj);
        return proprietarioRepository.salvar(proprietario);
    }
}
