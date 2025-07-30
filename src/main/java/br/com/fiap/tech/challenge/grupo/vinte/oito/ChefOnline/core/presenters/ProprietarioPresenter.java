package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common.PresenterMasks;

public class ProprietarioPresenter {
    public static ProprietarioDTO toDTO(Proprietario proprietario) {
        return new ProprietarioDTO(
                proprietario.getId(),
                proprietario.getNome(),
                PresenterMasks.maskEmail(proprietario.getEmail()),
                proprietario.getLogin(),
                PresenterMasks.maskValue(proprietario.getEndereco()),
                proprietario.getDataUltimaAlteracao()
        );
    }
}