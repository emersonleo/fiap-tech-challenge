package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.UsuarioDTO;

public class UsuarioPresenter {
    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getEndereco(),
                usuario.getDataUltimaAlteracao()
        );
    }
}
