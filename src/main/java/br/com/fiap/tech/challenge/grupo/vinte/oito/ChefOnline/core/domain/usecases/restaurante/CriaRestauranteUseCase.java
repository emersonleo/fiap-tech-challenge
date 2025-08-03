package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;

import java.util.Optional;

public class CriaRestauranteUseCase {
    private final IProprietarioGateway proprietarioGateway;
    private final IRestauranteGateway restauranteGateway;

    private CriaRestauranteUseCase(IProprietarioGateway proprietarioGateway, IRestauranteGateway restauranteGateway) {
        this.proprietarioGateway = proprietarioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static CriaRestauranteUseCase create(IProprietarioGateway proprietarioGateway, IRestauranteGateway restauranteGateway) {
        return new CriaRestauranteUseCase(proprietarioGateway, restauranteGateway);
    }

    public Restaurante run(NovoRestauranteDTO novoRestauranteDTO) {
        Optional<Proprietario> proprietario = proprietarioGateway.buscaProprietarioPorId(novoRestauranteDTO.idProprietario());

        if (proprietario.isEmpty()) {
            throw ProprietarioNotFoundException.withId(novoRestauranteDTO.idProprietario());
        }

        final Restaurante novoRestaurante = new Restaurante(
                null,
                novoRestauranteDTO.nomeRestaurante(),
                novoRestauranteDTO.endereco(),
                novoRestauranteDTO.tipoCozinha(),
                novoRestauranteDTO.horarioFuncionamento(),
                proprietario.get()
        );

        return restauranteGateway.adicionaRestaurante(novoRestaurante);
    }

}