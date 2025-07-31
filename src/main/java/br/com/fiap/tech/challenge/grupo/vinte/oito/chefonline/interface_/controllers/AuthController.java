package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.auth.LoginUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.auth.UpdatePasswordUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.auth.LoginRequest;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.auth.UpdatePasswordRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;

    public AuthController(LoginUseCase loginUseCase, UpdatePasswordUseCase updatePasswordUseCase) {
        this.loginUseCase = loginUseCase;
        this.updatePasswordUseCase = updatePasswordUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request) {
        loginUseCase.executar(request.login(), request.senha());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password")
    public ResponseEntity<Void> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        updatePasswordUseCase.executar(login, request.senhaAtual(), request.novaSenha());
        return ResponseEntity.ok().build();
    }
}
