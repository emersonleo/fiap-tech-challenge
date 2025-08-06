package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.restaurante.RestauranteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;

public class AtualizaRestauranteUseCase {
    final IRestauranteGateway restauranteGateway;

    private AtualizaRestauranteUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static AtualizaRestauranteUseCase create(IRestauranteGateway restauranteGateway) {
        return new AtualizaRestauranteUseCase(restauranteGateway);
    }

    public void run(AtualizaRestauranteDTO atualizaRestauranteDTO, Long id) {
        final Restaurante restauranteExistente = restauranteGateway.buscaRestaurantePorId(id)
                .orElseThrow(() -> new RestauranteNotFoundException(id));

        restauranteExistente.setNomeRestaurante(atualizaRestauranteDTO.nomeRestaurante());
        restauranteExistente.setEndereco(atualizaRestauranteDTO.endereco());
        restauranteExistente.setTipoCozinha(atualizaRestauranteDTO.tipoCozinha());
        restauranteExistente.setHorarioFuncionamento(atualizaRestauranteDTO.horarioFuncionamento());
        restauranteExistente.getProprietario().setId(atualizaRestauranteDTO.idProprietario());

        restauranteGateway.atualizaRestaurante(restauranteExistente);
    }

}
