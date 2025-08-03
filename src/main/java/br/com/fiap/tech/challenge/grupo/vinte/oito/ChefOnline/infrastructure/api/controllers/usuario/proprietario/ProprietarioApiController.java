package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ProprietarioController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.UsuarioDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proprietarios")
@Tag(name = "Proprietários", description = "API para gerenciamento de proprietários")
public class ProprietarioApiController {

    private final ProprietarioController proprietarioController;
    private final Logger logger = LoggerFactory.getLogger(ProprietarioApiController.class);

    public ProprietarioApiController(ProprietarioDataSource proprietarioDataSource, UsuarioDataSource usuarioDataSource) {
        this.proprietarioController = new ProprietarioController(proprietarioDataSource, usuarioDataSource);
    }

    @PostMapping
    @Operation(summary = "Criar um novo proprietário", description = "Cria um novo proprietário no sistema")
    @ApiResponse(responseCode = "201", description = "Proprietário criado com sucesso")
    public ResponseEntity<ProprietarioDTO> criarProprietario(@Valid @RequestBody NovoProprietarioDTO novoProprietarioDTO) {
        logger.info("POST -> /api/v1/proprietarios - Criando novo proprietário");
        ProprietarioDTO proprietarioCriado = proprietarioController.criaProprietario(novoProprietarioDTO);
        logger.info("Proprietário criado com sucesso, ID: {}", proprietarioCriado.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(proprietarioCriado);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os proprietários", description = "Lista todos os proprietários com paginação")
    @ApiResponse(responseCode = "200", description = "Lista de proprietários retornada com sucesso")
    public ResponseEntity<List<ProprietarioDTO>> buscaTodosProprietarios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /api/v1/proprietarios - Buscando todos os proprietários, página: {}, tamanho: {}", page, size);
        List<ProprietarioDTO> proprietarios = proprietarioController.buscaTodosProprietarios(page, size);
        logger.info("Retornados {} proprietários", proprietarios.size());
        return ResponseEntity.ok(proprietarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proprietário por ID", description = "Busca um proprietário específico pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Proprietário encontrado com sucesso")
    public ResponseEntity<ProprietarioDTO> buscarProprietarioPorId(
            @Parameter(description = "ID do proprietário", required = true) @PathVariable Long id) {
        logger.info("GET -> /api/v1/proprietarios/{} - Buscando proprietário por ID", id);
        ProprietarioDTO proprietario = proprietarioController.buscaProprietarioPorId(id);
        logger.info("Proprietário encontrado com sucesso, ID: {}", proprietario.id());
        return ResponseEntity.ok(proprietario);
    }

    @GetMapping("/login/{login}")
    @Operation(summary = "Buscar proprietário por login", description = "Busca um proprietário específico pelo seu login")
    @ApiResponse(responseCode = "200", description = "Proprietário encontrado com sucesso")
    public ResponseEntity<ProprietarioDTO> buscarProprietarioPorLogin(
            @Parameter(description = "Login do proprietário", required = true) @PathVariable String login) {
        logger.info("GET -> /api/v1/proprietarios/login/{} - Buscando proprietário por login", login);
        ProprietarioDTO proprietario = proprietarioController.buscaProprietarioPorLogin(login);
        logger.info("Proprietário encontrado com sucesso, login: {}", login);
        return ResponseEntity.ok(proprietario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar proprietário", description = "Atualiza os dados de um proprietário existente")
    @ApiResponse(responseCode = "200", description = "Proprietário atualizado com sucesso")
    public ResponseEntity<Void> atualizaProprietario(
            @Valid @RequestBody AtualizaProprietarioDTO atualizaProprietarioDTO,
            @Parameter(description = "ID do proprietário", required = true) @PathVariable("id") Long id) {
        logger.info("PUT -> /api/v1/proprietarios/{} - Atualizando proprietário", id);
        proprietarioController.atualizaProprietario(atualizaProprietarioDTO, id);
        logger.info("Proprietário atualizado com sucesso, ID: {}", id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/senha")
    @Operation(summary = "Alterar senha do proprietário", description = "Altera a senha de um proprietário mediante verificação da senha atual")
    @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso")
    public ResponseEntity<ProprietarioDTO> atualizaSenhaProprietario(@Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO) {
        logger.info("PUT -> /api/v1/proprietarios/senha - Alterando senha do proprietário login: {}", trocaSenhaDTO.login());
        ProprietarioDTO proprietarioAtualizado = proprietarioController.atualizaSenha(trocaSenhaDTO);
        logger.info("Senha alterada com sucesso para proprietário ID: {}", proprietarioAtualizado.id());
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar proprietário", description = "Remove um proprietário do sistema")
    @ApiResponse(responseCode = "204", description = "Proprietário deletado com sucesso")
    public ResponseEntity<Void> deletaProprietario(
            @Parameter(description = "ID do proprietário", required = true) @PathVariable("id") Long id) {
        logger.info("DELETE -> /api/v1/proprietarios/{} - Deletando proprietário", id);
        proprietarioController.deletaProprietario(id);
        logger.info("Proprietário deletado com sucesso, ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}