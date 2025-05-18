package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UsuarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(@RequestParam("page") int page,
                                                         @RequestParam("size") int size) {
        logger.info("GET -> /v1/usuarios");
        var usuarios = usuarioService.findAllUsers(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Void> criaUsuario(
            @RequestBody UsuarioDTO usuarioDTO
            ) {
        logger.info("POST -> /v1/usuarios");
        usuarioService.createUser(usuarioDTO);
        return ResponseEntity.status(201).build();
    }
}
