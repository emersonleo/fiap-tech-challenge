package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ClienteDTO;

public class ClientePresenter {
    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getLogin(),
                cliente.getSenha(),
                cliente.getEndereco(),
                cliente.getDataUltimaAlteracao()
        );
    }
}
