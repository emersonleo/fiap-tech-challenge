package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.ClienteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.cliente.ClienteNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.ClienteDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteApiController {

    private final ClienteController clienteController;

    public ClienteApiController(ClienteDataSource clienteDataSource) {
        this.clienteController = new ClienteController(clienteDataSource);
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody NovoClienteDTO novoClienteDTO) {
        try {
            ClienteDTO clienteCriado = clienteController.criaCliente(novoClienteDTO);

            if (clienteCriado == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Não foi possível criar o cliente. Verifique os dados informados.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar cliente: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            ClienteDTO cliente = clienteController.buscaClientePorId(id);

            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cliente não encontrado com o id: " + id);
            }

            return ResponseEntity.ok(cliente);
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar cliente: " + e.getMessage());
        }
    }
}
