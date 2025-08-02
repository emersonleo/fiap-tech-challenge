package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.api.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ProprietarioController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.UsuarioDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/proprietarios")
@Tag(name = "Proprietários", description = "API para gerenciamento de proprietários")
public class ProprietarioApiController {

    private final ProprietarioController proprietarioController;

    public ProprietarioApiController(ProprietarioDataSource proprietarioDataSource, UsuarioDataSource usuarioDataSource) {
        this.proprietarioController = new ProprietarioController(proprietarioDataSource, usuarioDataSource);
    }

    @PostMapping
    @Operation(summary = "Criar um novo proprietário", description = "Cria um novo proprietário no sistema")
    @ApiResponse(responseCode = "201", description = "Proprietário criado com sucesso")
    public ResponseEntity<ProprietarioDTO> criarProprietario(@Valid @RequestBody NovoProprietarioDTO novoProprietarioDTO) {
        ProprietarioDTO proprietarioCriado = proprietarioController.criaProprietario(novoProprietarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proprietarioCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proprietário por ID", description = "Busca um proprietário específico pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Proprietário encontrado com sucesso")
    public ResponseEntity<ProprietarioDTO> buscarProprietarioPorId(
        @Parameter(description = "ID do proprietário", required = true) @PathVariable Long id) {
        ProprietarioDTO proprietario = proprietarioController.buscaProprietarioPorId(id);
        return ResponseEntity.ok(proprietario);
    }
}