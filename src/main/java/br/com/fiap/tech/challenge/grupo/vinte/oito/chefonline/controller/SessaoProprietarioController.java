package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UsuarioLoginDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service.SessaoProprietarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sessao/proprietarios")
public class SessaoProprietarioController {
    private static final Logger logger = LoggerFactory.getLogger(SessaoClienteController.class);

    private final SessaoProprietarioService sessaoProprietarioService;

    SessaoProprietarioController(SessaoProprietarioService sessaoProprietarioService) {
        this.sessaoProprietarioService = sessaoProprietarioService;
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(
            @RequestBody UsuarioLoginDTO usuarioLoginDTO
    ) {
        logger.info("POST -> /v1/sessao/proprietarios/login");

        sessaoProprietarioService.login(usuarioLoginDTO.login(), usuarioLoginDTO.senha());

        return ResponseEntity.ok("usuario logado");
    }
}
