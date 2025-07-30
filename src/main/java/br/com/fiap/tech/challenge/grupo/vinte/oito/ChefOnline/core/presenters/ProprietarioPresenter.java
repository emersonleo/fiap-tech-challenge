package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;

public class ProprietarioPresenter {
    public static ProprietarioDTO toDTO(Proprietario proprietario) {
        return new ProprietarioDTO(
                proprietario.getId(),
                proprietario.getNome(),
                proprietario.getEmail(),
                proprietario.getLogin(),
                proprietario.getSenha(),
                proprietario.getEndereco(),
                proprietario.getDataUltimaAlteracao()
        );
    }
}