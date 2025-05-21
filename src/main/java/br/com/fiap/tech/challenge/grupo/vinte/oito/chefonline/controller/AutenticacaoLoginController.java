package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.LoginRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service.AutenticacaoLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class AutenticacaoLoginController {

    private final AutenticacaoLoginService autenticacaoLoginService;

    public AuthController(AutenticacaoLoginService autenticacaoLoginService) {
        this.autenticacaoLoginService = autenticacaoLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        if (autenticacaoLoginService.validaLogin(loginRequestDTO)) {
            return ResponseEntity.ok("Login bem-sucedido");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
}
