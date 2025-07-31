package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.CriarClienteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.cliente.ClienteRequest;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.cliente.ClienteResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final CriarClienteUseCase criarClienteUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = criarClienteUseCase.executar(
            request.nome(),
            request.email(),
            request.login(),
            request.senha(),
            request.endereco(),
            request.cpf()
        );

        return ResponseEntity.ok(new ClienteResponse(
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getLogin(),
            cliente.getEndereco(),
            cliente.getCpf()
        ));
    }
}
