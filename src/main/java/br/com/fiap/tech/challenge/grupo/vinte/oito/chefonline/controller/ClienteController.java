package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller.swagger.ClienteControllerSwagger;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteResponseDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UpdatePasswordDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service.ClienteService;
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
@RequestMapping("/v1/clientes")
public class ClienteController implements ClienteControllerSwagger {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> criaCliente(
            @Valid @RequestBody ClienteRequestDTO clienteRequestDTO
            ) {
        logger.info("POST -> /v1/clientes");
        clienteService.criaCliente(clienteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> buscaTodosClientes(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /v1/clientes");
        var clientes = clienteService.buscaTodosClientes(page, size);
        return ResponseEntity.ok(clientes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscaClientePorId(
            @PathVariable("id") Long id) {
        logger.info("GET -> /v1/clientes/{}", id);
        var cliente = clienteService.buscaClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @Override
    @PutMapping({"/{id}"})
    public ResponseEntity<Void> atualizaCliente(
            @Valid @RequestBody ClienteRequestDTO clienteRequestDTO,
            @PathVariable("id") Long id) {
        logger.info("PUT -> /v1/clientes/{}", id);
        clienteService.atualizaCliente(clienteRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(
            @PathVariable("id") Long id) {
        logger.info("DELETE -> /v1/clientes/{}", id);
        clienteService.deletaCliente(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PatchMapping
    public ResponseEntity<Void> atualizaSenha(
            @Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        logger.info("PATCH -> /v1/clientes");
        clienteService.atualizaSenha(updatePasswordDTO);
        return ResponseEntity.ok().build();
    }
}
