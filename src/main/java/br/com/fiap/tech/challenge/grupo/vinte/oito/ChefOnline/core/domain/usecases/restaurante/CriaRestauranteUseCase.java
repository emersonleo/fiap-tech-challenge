package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteGateway;

public class CriaRestauranteUseCase {
    final IClienteGateway clienteGateway;
    final IRestauranteGateway restauranteGateway;

    public CriaRestauranteUseCase(IClienteGateway clienteGateway, IRestauranteGateway restauranteGateway) {
        this.clienteGateway = clienteGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static CriaRestauranteUseCase create(IClienteGateway clienteGateway, IRestauranteGateway restauranteGateway) {
        return new CriaRestauranteUseCase(clienteGateway, restauranteGateway);
    }

    public Restaurante run(NovoRestauranteDTO novoRestauranteDTO) {
        boolean isExisteCliente = clienteGateway.buscaClientePorId(novoRestauranteDTO.idDonoRestaurante()).isPresent();

        if (!isExisteCliente) {
            throw new ClienteNotFoundException(novoRestauranteDTO.idDonoRestaurante());
        }

        final Restaurante novoRestaurante = new Restaurante(
                novoRestauranteDTO.nomeRestaurante(),
                novoRestauranteDTO.endereco(),
                novoRestauranteDTO.tipoCozinha(),
                novoRestauranteDTO.horarioFuncionamento(),
                novoRestauranteDTO.idDonoRestaurante()
        );

        return restauranteGateway.adicionaRestaurante(novoRestaurante);
    }

}