package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ProprietarioController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger.IProprietarioApiControllerSwagger;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.UsuarioDataSource;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/v1/proprietarios")
public class ProprietarioApiController implements IProprietarioApiControllerSwagger {

    private final ProprietarioController proprietarioController;
    private final Logger logger = LoggerFactory.getLogger(ProprietarioApiController.class);

    public ProprietarioApiController(ProprietarioDataSource proprietarioDataSource, UsuarioDataSource usuarioDataSource) {
        this.proprietarioController = new ProprietarioController(proprietarioDataSource, usuarioDataSource);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProprietarioDTO> criaProprietario(@Valid @RequestBody NovoProprietarioDTO novoProprietarioDTO) {
        logger.info("POST -> /api/v1/proprietarios - Criando novo proprietário");
        ProprietarioDTO proprietarioCriado = proprietarioController.criaProprietario(novoProprietarioDTO);
        logger.info("Proprietário criado com sucesso, ID: {}", proprietarioCriado.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(proprietarioCriado);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProprietarioDTO>> buscaTodosProprietarios(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET -> /api/v1/proprietarios - Buscando todos os proprietários, página: {}, tamanho: {}", page, size);
        List<ProprietarioDTO> proprietarios = proprietarioController.buscaTodosProprietarios(page, size);
        logger.info("Retornados {} proprietários", proprietarios.size());
        return ResponseEntity.ok(proprietarios);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioDTO> buscaProprietarioPorId(
            @Parameter(description = "ID do proprietário", required = true) @PathVariable Long id) {
        logger.info("GET -> /api/v1/proprietarios/{} - Buscando proprietário por ID", id);
        ProprietarioDTO proprietario = proprietarioController.buscaProprietarioPorId(id);
        logger.info("Proprietário encontrado com sucesso, ID: {}", proprietario.id());
        return ResponseEntity.ok(proprietario);
    }

//    @Override
    @GetMapping("/login/{login}")
    public ResponseEntity<ProprietarioDTO> buscarProprietarioPorLogin(
            @Parameter(description = "Login do proprietário", required = true) @PathVariable String login) {
        logger.info("GET -> /api/v1/proprietarios/login/{} - Buscando proprietário por login", login);
        ProprietarioDTO proprietario = proprietarioController.buscaProprietarioPorLogin(login);
        logger.info("Proprietário encontrado com sucesso, login: {}", login);
        return ResponseEntity.ok(proprietario);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizaProprietario(
            @Valid @RequestBody AtualizaProprietarioDTO atualizaProprietarioDTO,
            @Parameter(description = "ID do proprietário", required = true) @PathVariable("id") Long id) {
        logger.info("PUT -> /api/v1/proprietarios/{} - Atualizando proprietário", id);
        proprietarioController.atualizaProprietario(atualizaProprietarioDTO, id);
        logger.info("Proprietário atualizado com sucesso, ID: {}", id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/senha")
    public ResponseEntity<ProprietarioDTO> atualizaSenhaProprietario(@Valid @RequestBody TrocaSenhaDTO trocaSenhaDTO) {
        logger.info("PUT -> /api/v1/proprietarios/senha - Alterando senha do proprietário login: {}", trocaSenhaDTO.login());
        ProprietarioDTO proprietarioAtualizado = proprietarioController.atualizaSenha(trocaSenhaDTO);
        logger.info("Senha alterada com sucesso para proprietário ID: {}", proprietarioAtualizado.id());
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProprietario(
            @Parameter(description = "ID do proprietário", required = true) @PathVariable("id") Long id) {
        logger.info("DELETE -> /api/v1/proprietarios/{} - Deletando proprietário", id);
        proprietarioController.deletaProprietario(id);
        logger.info("Proprietário deletado com sucesso, ID: {}", id);
        return ResponseEntity.noContent().build();
    }

}