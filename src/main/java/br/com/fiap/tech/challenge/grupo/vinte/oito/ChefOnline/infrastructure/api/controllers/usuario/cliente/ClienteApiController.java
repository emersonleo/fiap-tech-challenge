package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.usuario.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ClienteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.AtualizaClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.cliente.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger.IClienteApiControllerSwagger;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.UsuarioDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteApiController implements IClienteApiControllerSwagger {

    private final ClienteController clienteController;
    private final Logger logger = LoggerFactory.getLogger(ClienteApiController.class);

    public ClienteApiController(ClienteDataSource clienteDataSource, UsuarioDataSource usuarioDataSource) {
        this.clienteController = new ClienteController(clienteDataSource, usuarioDataSource);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody NovoClienteDTO novoClienteDTO) {
        logger.info("POST -> /api/v1/clientes - Criando novo cliente");
        ClienteDTO clienteCriado = clienteController.criaCliente(novoClienteDTO);
        logger.info("Cliente criado com sucesso, ID: {}", clienteCriado.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(
            @Parameter(description = "ID do cliente", required = true) @PathVariable Long id) {
        logger.info("GET -> /api/v1/clientes/{} - Buscando cliente por ID", id);
        ClienteDTO cliente = clienteController.buscaClientePorId(id);
        logger.info("Cliente encontrado com sucesso, ID: {}", cliente.id());
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodosClientes(
            @Parameter(description = "Número da página") @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Tamanho da página") @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /api/v1/clientes - Buscando todos os clientes (página: {}, tamanho: {})", page, size);
        List<ClienteDTO> clientes = clienteController.buscaTodosClientes(page, size);
        logger.info("Encontrados {} clientes", clientes.size());
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<ClienteDTO> buscarClientePorLogin(
            @Parameter(description = "Login do cliente", required = true) @PathVariable String login) {
        logger.info("GET -> /api/v1/clientes/login/{} - Buscando cliente por login", login);
        ClienteDTO cliente = clienteController.buscaClientePorLogin(login);
        logger.info("Cliente encontrado com sucesso, login: {}", login);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCliente(
            @Valid @RequestBody AtualizaClienteDTO atualizaClienteDTO,
            @Parameter(description = "ID do cliente", required = true) @PathVariable("id") Long id) {
        logger.info("PUT -> /api/v1/clientes/{} - Atualizando cliente", id);
        clienteController.atualizaCliente(atualizaClienteDTO, id);
        logger.info("Cliente atualizado com sucesso, ID: {}", id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/senha")
    public ResponseEntity<ClienteDTO> atualizarSenhaCliente(@Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO) {
        logger.info("PUT -> /api/v1/clientes/senha - Alterando senha do cliente login: {}", trocaSenhaDTO.login());
        ClienteDTO clienteAtualizado = clienteController.atualizaSenha(trocaSenhaDTO);
        logger.info("Senha alterada com sucesso para cliente, ID: {}", clienteAtualizado.id());
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(
            @Parameter(description = "ID do cliente", required = true) @PathVariable("id") Long id) {
        logger.info("DELETE -> /api/v1/clientes/{} - Deletando cliente", id);
        clienteController.deletaCliente(id);
        logger.info("Cliente deletado com sucesso, ID: {}", id);
        return ResponseEntity.noContent().build();
    }

}
