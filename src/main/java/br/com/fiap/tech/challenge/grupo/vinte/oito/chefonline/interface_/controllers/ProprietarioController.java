package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.CriarProprietarioUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.proprietario.ProprietarioRequest;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.proprietario.ProprietarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proprietarios")
public class ProprietarioController {
    private final CriarProprietarioUseCase criarProprietarioUseCase;

    public ProprietarioController(CriarProprietarioUseCase criarProprietarioUseCase) {
        this.criarProprietarioUseCase = criarProprietarioUseCase;
    }

    @PostMapping
    public ResponseEntity<ProprietarioResponse> criar(@Valid @RequestBody ProprietarioRequest request) {
        Proprietario proprietario = criarProprietarioUseCase.executar(
            request.nome(),
            request.email(),
            request.login(),
            request.senha(),
            request.endereco(),
            request.cnpj()
        );

        return ResponseEntity.ok(new ProprietarioResponse(
            proprietario.getNome(),
            proprietario.getEmail(),
            proprietario.getLogin(),
            proprietario.getEndereco(),
            proprietario.getCnpj()
        ));
    }
}
