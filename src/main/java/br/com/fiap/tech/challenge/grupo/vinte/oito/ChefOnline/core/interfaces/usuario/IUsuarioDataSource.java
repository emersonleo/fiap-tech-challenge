package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioDataSource {
    Usuario adicionaUsuario(Usuario novoUsuario);
    Optional<Usuario> buscaUsuarioPorEmail(String email);
    Optional<Usuario> buscaUsuarioPorId(Long id);
    List<Usuario> buscaTodosUsuarios(int page, int size);
    Usuario atualizaUsuario(Usuario usuario);
    Optional<Usuario> buscaUsuarioPorLogin(String login);
    void deletaUsuario(Usuario usuario);
}