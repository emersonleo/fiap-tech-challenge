package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UsuarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service.ClienteService;
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
@RequestMapping("/v1/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Void> criaCliente(
            @RequestBody ClienteDTO clienteDTO
            ) {
        logger.info("POST -> /v1/clientes");
        clienteService.criaCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscaTodosClientes(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /v1/clientes");
        var clientes = clienteService.buscaTodosClientes(page, size);
        return ResponseEntity.ok(clientes);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Void> atualizaCliente(
            @RequestBody ClienteDTO clienteDTO,
            @PathVariable("id") Long id) {
        logger.info("PUT -> /v1/clientes/{id}", id);
        clienteService.atualizaCliente(clienteDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(
            @PathVariable("id") Long id) {
        logger.info("DELETE -> /v1/clientes/{id}", id);
        clienteService.deletaCliente(id);
        return ResponseEntity.ok().build();
    }

}
