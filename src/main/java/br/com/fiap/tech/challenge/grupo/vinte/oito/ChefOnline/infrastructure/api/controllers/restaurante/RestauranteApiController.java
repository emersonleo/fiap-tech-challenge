package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger.IRestauranteApiControllerSwagger;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante.RestauranteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante.RestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ProprietarioDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@Tag(name = "Restaurantes", description = "API para gerenciamento de restaurantes")
public class RestauranteApiController implements IRestauranteApiControllerSwagger {

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

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> buscaTodosRestaurantes(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /api/v1/restaurantes - Buscando todos os restaurantes, página: {}, tamanho: {}", page, size);
        List<RestauranteDTO> restaurantes = restauranteController.buscaTodosRestaurantes(page, size);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID", description = "Busca um restaurante específico pelo seu ID")
    public ResponseEntity<RestauranteDTO> buscaRestaurantePorId(@PathVariable Long id) {
        logger.info("GET -> /api/v1/restaurantes/{} - Buscando restaurante por ID", id);
        RestauranteDTO restaurante = restauranteController.buscaRestaurantePorId(id);
        logger.info("Restaurante encontrado com sucesso, ID: {}", restaurante.id());
        return ResponseEntity.ok(restaurante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizaRestaurante(
            @Valid @RequestBody AtualizaRestauranteDTO atualizaRestauranteDTO,
            @PathVariable("id") Long id) {
        logger.info("PUT -> /api/v1/restaurantes/{} - Atualizando restaurante", id);
        restauranteController.atualizaRestaurante(atualizaRestauranteDTO, id);
        logger.info("Restaurante atualizado com sucesso, ID: {}", id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaRestaurante(@PathVariable("id") Long id) {
        logger.info("DELETE -> /api/v1/restaurantes/{} - Deletando restaurante", id);
        restauranteController.deletaRestaurante(id);
        logger.info("Restaurante deletado com sucesso, ID: {}", id);
        return ResponseEntity.noContent().build();
    }

}