package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.ConverterTipoUsuarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.UsuarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.UsuarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.UsuarioPresenter;

public class UsuarioConverterController {
    private final IUsuarioDataSource usuarioDataSource;
    private final IRestauranteDataSource restauranteDataSource;

    public UsuarioConverterController(IUsuarioDataSource usuarioDataSource, IRestauranteDataSource restauranteDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.restauranteDataSource = restauranteDataSource;
    }

    public UsuarioDTO converterTipoUsuario(Long usuarioId, NomeDoTipo tipoDestino) {
        var usuarioGateway = UsuarioGateway.create(usuarioDataSource);
        var restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        var converterTipoUsuarioUseCase = ConverterTipoUsuarioUseCase.create(usuarioGateway, restauranteGateway);

        var usuario = converterTipoUsuarioUseCase.run(usuarioId, tipoDestino);
        return UsuarioPresenter.toDTO(usuario);
    }
}
