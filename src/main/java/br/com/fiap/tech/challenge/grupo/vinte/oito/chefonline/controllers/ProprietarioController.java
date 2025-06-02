package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controllers.swagger.ProprietarioControllerSwagger;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dtos.ProprietarioRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dtos.ProprietarioResponseDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dtos.UpdatePasswordDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services.ProprietarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class ProprietarioController implements ProprietarioControllerSwagger {

    private static final Logger logger = LoggerFactory.getLogger(ProprietarioController.class);

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> criaProprietario(
            @Valid @RequestBody ProprietarioRequestDTO proprietarioRequestDTO
    ) {
        logger.info("POST -> /v1/proprietarios");
        proprietarioService.criaProprietario(proprietarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProprietarioResponseDTO>> buscaTodosProprietarios(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /v1/proprietarios");
        var proprietarios = proprietarioService.buscaTodosProprietarios(page, size);
        return ResponseEntity.ok(proprietarios);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioResponseDTO> buscaProprietarioPorId(
            @PathVariable("id") Long id) {
        logger.info("GET -> /v1/proprietarios/{}", id);
        ProprietarioResponseDTO proprietario = proprietarioService.buscaProprietarioPorId(id);
        return ResponseEntity.ok(proprietario);
    }

    @Override
    @PutMapping({"/{id}"})
    public ResponseEntity<Void> atualizaProprietario(
            @Valid @RequestBody ProprietarioRequestDTO proprietarioRequestDTO,
            @PathVariable("id") Long id) {
        logger.info("PUT -> /v1/proprietarios/{}", id);
        proprietarioService.atualizaProprietario(proprietarioRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProprietario(
            @PathVariable("id") Long id) {
        logger.info("DELETE -> /v1/proprietarios/{}", id);
        proprietarioService.deletaProprietario(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping
    public ResponseEntity<Void> atualizaSenha(
            @Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        logger.info("PATCH -> /v1/proprietarios");
        proprietarioService.atualizaSenhaProprietario(updatePasswordDTO);
        return ResponseEntity.ok().build();
    }

}
