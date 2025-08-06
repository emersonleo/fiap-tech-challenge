package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.UsuarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.UsuarioGateway;

public class ConverterTipoUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;
    private final RestauranteGateway restauranteGateway;

    private ConverterTipoUsuarioUseCase(UsuarioGateway usuarioGateway, RestauranteGateway restauranteGateway) {
        this.usuarioGateway = usuarioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static ConverterTipoUsuarioUseCase create(UsuarioGateway usuarioGateway, RestauranteGateway restauranteGateway) {
        return new ConverterTipoUsuarioUseCase(usuarioGateway, restauranteGateway);
    }

    public Usuario run(Long usuarioId, NomeDoTipo tipoDestino) {
        Usuario usuario = usuarioGateway.buscaUsuarioPorId(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));

        if (usuario.getTipo().equals(tipoDestino)) {
            return usuario;
        }

        if (usuario.getTipo().equals(NomeDoTipo.PROPRIETARIO)) {
            restauranteGateway.deletaRestaurantePorProprietarioId(usuarioId);
        }

        Usuario usuarioConvertido = usuario.convertByTipo(tipoDestino);

        return usuarioGateway.atualizaUsuario(usuarioConvertido);
    }
}
