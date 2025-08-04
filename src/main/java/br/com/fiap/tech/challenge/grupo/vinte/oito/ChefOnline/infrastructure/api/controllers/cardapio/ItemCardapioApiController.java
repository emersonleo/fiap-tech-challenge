package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.cardapio;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.cardapio.ItemCardapioController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.ItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger.IItemCardapioApiControllerSwagger;
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
@RequestMapping("/api/v1/cardapios")
public class ItemCardapioApiController implements IItemCardapioApiControllerSwagger {

    private final Logger logger = LoggerFactory.getLogger(ItemCardapioApiController.class);

    private final ItemCardapioController itemCardapioController;

    public ItemCardapioApiController(ItemCardapioController itemCardapioController) {
        this.itemCardapioController = itemCardapioController;
    }

    @Override
    @PostMapping
    public ResponseEntity<ItemCardapioDTO> criarItemCardapio(
            @Valid @RequestBody NovoItemCardapioDTO novoItemCardapioDTO) {

        logger.info("POST -> /api/v1/cardapio - Adicionando item ao cardápio");

        ItemCardapioDTO novoItem = itemCardapioController.criaItemCardapio(novoItemCardapioDTO);
        logger.info("Item adicionado ao cardápio do restaurante {}", novoItemCardapioDTO.idRestaurante());

        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ItemCardapioDTO>> buscarTodosItensCardapio(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /api/v1/cardapio - Buscando itens do cardápio, página: {}, tamanho: {}", page, size);
        List<ItemCardapioDTO> itens = itemCardapioController.buscaTodosItensCardapio(page, size);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<ItemCardapioDTO>> buscarTodosItensCardapioRestaurante(
            @PathVariable Long restauranteId) {
        logger.info("GET -> /api/v1/cardapio - Buscando itens do cardápio por restaurante ID: {}", restauranteId);
        List<ItemCardapioDTO> itens = itemCardapioController.buscaItensCardapioPorRestaurante(restauranteId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemCardapioDTO> buscarItemCardapioPorId(
            @PathVariable Long itemId) {

        logger.info("GET -> /api/v1/cardapio/{} - Buscando item do cardápio por ID", itemId);
        ItemCardapioDTO item = itemCardapioController.buscaItemCardapioPorId(itemId);

        logger.info("Item do cardápio encontrado com sucesso, ID: {}", itemId);
        return ResponseEntity.ok(item);
    }

    @Override
    @PutMapping("/{itemId}")
    public ResponseEntity<Void> atualizarItemCardapio(
            @Valid @RequestBody AtualizaItemCardapioDTO atualizaItemCardapioDTO,
            @PathVariable Long itemId) {

        logger.info("PUT -> /api/v1/cardapio/- Atualizando item {} do cardápio", itemId);
        itemCardapioController.atualizaItemCardapio(itemId, atualizaItemCardapioDTO);
        logger.info("Item do cardápio atualizado com sucesso, ID: {}", itemId);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deletarItemCardapio(
            @PathVariable("itemId") Long itemId) {

        logger.info("DELETE -> /api/v1/cardapio/{} - Deletando item do cardápio", itemId);
        itemCardapioController.deletaItemCardapio(itemId);

        logger.info("Item do cardápio deletado com sucesso, ID: {}", itemId);
        return ResponseEntity.noContent().build();
    }

}