package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.UsuarioRepositoryPort;

public class CriarUsuarioUseCase {
    private final UsuarioRepositoryPort usuarioRepository;

    public CriarUsuarioUseCase(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario executar(String nome, String email, String login, String senha, String endereco) {
        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (usuarioRepository.buscarPorLogin(login).isPresent()) {
            throw new IllegalArgumentException("Login já cadastrado");
        }

        Usuario novoUsuario = new Usuario(nome, email, login, senha, endereco);
        return usuarioRepository.salvar(novoUsuario);
    }
}
