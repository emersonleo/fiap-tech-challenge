package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service.ProprietarioService;
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
@RequestMapping("/v1/proprietarios")
public class ProprietarioController {

    private static final Logger logger = LoggerFactory.getLogger(ProprietarioController.class);

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @PostMapping
    public ResponseEntity<Void> criaProprietario(
            @RequestBody ProprietarioDTO proprietarioDTO
    ) {
        logger.info("POST -> /v1/proprietarios");
        proprietarioService.criaProprietario(proprietarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Proprietario>> buscaTodosProprietarios(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /v1/proprietarios");
        var proprietarios = proprietarioService.buscaTodosProprietarios(page, size);
        return ResponseEntity.ok(proprietarios);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Void> atualizaProprietario(
            @RequestBody ProprietarioDTO proprietarioDTO,
            @PathVariable("id") Long id) {
        logger.info("PUT -> /v1/proprietarios/{id}", id);
        proprietarioService.atualizaProprietario(proprietarioDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProprietario(
            @PathVariable("id") Long id) {
        logger.info("DELETE -> /v1/proprietarios/{id}", id);
        proprietarioService.deletaProprietario(id);
        return ResponseEntity.ok().build();
    }

}
