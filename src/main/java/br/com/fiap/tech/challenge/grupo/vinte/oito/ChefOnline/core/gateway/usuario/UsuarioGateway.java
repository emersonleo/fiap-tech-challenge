package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioGateway;

import java.util.List;
import java.util.Optional;

public class UsuarioGateway implements IUsuarioGateway {
    private final IUsuarioDataSource usuarioDataSource;

    private UsuarioGateway(IUsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public static UsuarioGateway create(IUsuarioDataSource usuarioDataSource) {
       return new UsuarioGateway(usuarioDataSource);
    }

    @Override
    public Usuario adicionaUsuario(Usuario novoUsuario) {
        return usuarioDataSource.adicionaUsuario(novoUsuario);
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorEmail(String email) {
        return usuarioDataSource.buscaUsuarioPorEmail(email);
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorId(Long id) {
        return usuarioDataSource.buscaUsuarioPorId(id);
    }

    @Override
    public List<Usuario> buscaTodosUsuarios(int page, int size) {
        return usuarioDataSource.buscaTodosUsuarios(page, size);
    }

    @Override
    public Usuario atualizaUsuario(Usuario usuario) {
        return usuarioDataSource.atualizaUsuario(usuario);
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorLogin(String login) {
        return usuarioDataSource.buscaUsuarioPorLogin(login);
    }

    @Override
    public void deletaUsuario(Usuario usuario) {
        usuarioDataSource.deletaUsuario(usuario);
    }
}