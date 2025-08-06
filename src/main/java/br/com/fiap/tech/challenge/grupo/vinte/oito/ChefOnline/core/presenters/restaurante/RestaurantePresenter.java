package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.ProprietarioPresenter;

public class RestaurantePresenter {
    public static RestauranteDTO toDTO(Restaurante restaurante) {
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNomeRestaurante(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                proprietarioToDTO(restaurante.getProprietario())
        );
    }

    private static ProprietarioDTO proprietarioToDTO(Proprietario proprietario) {
        return ProprietarioPresenter.toDTO(proprietario);
    }

}