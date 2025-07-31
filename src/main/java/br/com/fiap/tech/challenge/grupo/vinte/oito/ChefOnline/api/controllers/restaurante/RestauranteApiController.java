package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api.controllers.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante.RestauranteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante.RestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ClienteDataSource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurantes", description = "API para gerenciamento de restaurantes")
public class RestauranteApiController {

    private final Logger logger = LoggerFactory.getLogger(RestauranteApiController.class);

    private final RestauranteController restauranteController;


    public RestauranteApiController(ClienteDataSource clienteDataSource, RestauranteDataSource restauranteDataSource) {
        this.restauranteController = new RestauranteController(clienteDataSource, restauranteDataSource);
    }
}