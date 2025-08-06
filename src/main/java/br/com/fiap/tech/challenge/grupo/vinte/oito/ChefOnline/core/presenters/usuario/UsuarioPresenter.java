package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.UsuarioDTO;

public class UsuarioPresenter {
    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            usuario.getEndereco(),
            usuario.getTipo(),
            usuario.getDataUltimaAlteracao()
        );
    }
}
