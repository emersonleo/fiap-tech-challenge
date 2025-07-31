package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.CriarUsuarioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final CriarUsuarioUseCase criarUsuarioUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody CriarUsuarioRequest request) {
        Usuario usuario = criarUsuarioUseCase.executar(
            request.nome(),
            request.email(),
            request.login(),
            request.senha(),
            request.endereco()
        );

        return ResponseEntity.ok(new UsuarioResponse(
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            usuario.getEndereco()
        ));
    }
}
