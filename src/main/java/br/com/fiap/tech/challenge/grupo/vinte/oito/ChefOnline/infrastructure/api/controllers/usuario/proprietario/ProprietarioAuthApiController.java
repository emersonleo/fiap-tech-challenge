package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.ProprietarioAuthController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.LoginResponseProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.ProprietarioAuthDataSource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/proprietarios")
@Tag(name = "Proprietário Auth", description = "API para autenticação de proprietários")
public class ProprietarioAuthApiController {
    private final ProprietarioAuthController proprietarioAuthController;

    public ProprietarioAuthApiController(ProprietarioAuthDataSource proprietarioAuthDataSource) {
        this.proprietarioAuthController = new ProprietarioAuthController(proprietarioAuthDataSource);
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody VerificaCredenciaisDTO verificaCredenciaisDTO) {
        LoginResponseProprietarioDTO responseProprietarioDTO = proprietarioAuthController.login(verificaCredenciaisDTO);
        return ResponseEntity.ok(responseProprietarioDTO);
    }
}
