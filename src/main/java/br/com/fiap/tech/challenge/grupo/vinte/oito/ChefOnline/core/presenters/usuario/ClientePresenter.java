package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common.PresenterMasks;

public class ClientePresenter {
    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                PresenterMasks.maskEmail(cliente.getEmail()),
                cliente.getLogin(),
                PresenterMasks.maskValue(cliente.getEndereco()),
                cliente.getDataUltimaAlteracao()
        );
    }
}
