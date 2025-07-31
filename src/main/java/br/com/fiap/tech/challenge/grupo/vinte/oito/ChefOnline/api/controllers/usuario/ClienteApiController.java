package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ClienteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ClienteDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteApiController {

    private final ClienteController clienteController;
    private final Logger logger = LoggerFactory.getLogger(ClienteApiController.class);

    public ClienteApiController(ClienteDataSource clienteDataSource) {
        this.clienteController = new ClienteController(clienteDataSource);
    }

    @PostMapping
    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente no sistema")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso")
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
        logger.info("POST -> /api/clientes - Criando novo cliente");
        ClienteDTO clienteCriado = clienteController.criaCliente(novoClienteDTO);
        logger.info("Cliente criado com sucesso, ID: {}", clienteCriado.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente específico pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso")
    public ResponseEntity<ClienteDTO> buscarClientePorId(
        @Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
        logger.info("GET -> /api/clientes/{} - Buscando cliente por ID", id);
        ClienteDTO cliente = clienteController.buscaClientePorId(id);
        logger.info("Cliente encontrado com sucesso, ID: {}", cliente.id());
        return ResponseEntity.ok(cliente);
    }
}
