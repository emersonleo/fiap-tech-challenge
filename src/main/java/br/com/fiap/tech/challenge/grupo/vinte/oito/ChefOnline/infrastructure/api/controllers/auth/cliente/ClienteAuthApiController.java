package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.auth.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.auth.cliente.ClienteAuthController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.LoginResponseClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthDataSource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth/clientes")
@Tag(name = "Cliente Auth", description = "API para autenticação de clientes")
public class ClienteAuthApiController {
    private final ClienteAuthController clienteAuthController;

    public ClienteAuthApiController(IClienteAuthDataSource clienteAuthDataSource) {
        this.clienteAuthController = new ClienteAuthController(clienteAuthDataSource);
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody VerificaCredenciaisDTO verificaCredenciaisDTO) {
        LoginResponseClienteDTO responseClienteDTO = clienteAuthController.login(verificaCredenciaisDTO);
        return ResponseEntity.ok(responseClienteDTO);
    }
}
