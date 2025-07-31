package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepositoryPort {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    Optional<Usuario> buscarPorLogin(String login);
    List<Usuario> buscarTodos();
    void deletar(String login);
}
