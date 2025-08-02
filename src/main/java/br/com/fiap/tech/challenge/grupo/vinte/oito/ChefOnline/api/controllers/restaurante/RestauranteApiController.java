package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api.controllers.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante.RestauranteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante.RestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ProprietarioDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurantes", description = "API para gerenciamento de restaurantes")
public class RestauranteApiController {

    private final Logger logger = LoggerFactory.getLogger(RestauranteApiController.class);

    private final RestauranteController restauranteController;

    public RestauranteApiController(ProprietarioDataSource proprietarioDataSource, RestauranteDataSource restauranteDataSource) {
        this.restauranteController = new RestauranteController(proprietarioDataSource, restauranteDataSource);
    }

    @PostMapping
    @Operation(summary = "Criar um novo restaurante", description = "Cria um novo restaurante no sistema")
    public ResponseEntity<RestauranteDTO> criaRestaurante(@Valid @RequestBody NovoRestauranteDTO novoRestauranteDTO) {
        logger.info("POST -> /api/restaurantes - Criando novo restaurante");
        RestauranteDTO restauranteCriado = restauranteController.criaRestaurante(novoRestauranteDTO);
        logger.info("Restaurante criado com sucesso, ID: {}", restauranteCriado.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID", description = "Busca um restaurante específico pelo seu ID")
    public ResponseEntity<RestauranteDTO> buscaRestaurantePorId(@PathVariable Long id) {
        logger.info("GET -> /api/restaurantes/{} - Buscando restaurante por ID", id);
        RestauranteDTO restaurante = restauranteController.buscaRestaurantePorId(id);
        logger.info("Restaurante encontrado com sucesso, ID: {}", restaurante.id());
        return ResponseEntity.ok(restaurante);
    }

}