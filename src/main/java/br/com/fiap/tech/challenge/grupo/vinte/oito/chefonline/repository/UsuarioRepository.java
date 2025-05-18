package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Usuario;

import java.util.List;

public interface UsuarioRepository {

    Integer save(Usuario usuario);

    List<Usuario> findAllUsers(int size, int offset);

}
