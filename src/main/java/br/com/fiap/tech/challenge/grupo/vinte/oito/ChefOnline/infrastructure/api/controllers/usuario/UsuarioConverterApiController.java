package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario.UsuarioConverterController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.UsuarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante.RestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario.UsuarioDataSource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Conversor de Usuários", description = "API para conversão entre tipos de usuários")
public class UsuarioConverterApiController {
    private final UsuarioConverterController usuarioConverterController;
    private final Logger logger = LoggerFactory.getLogger(UsuarioConverterApiController.class);

    public UsuarioConverterApiController(UsuarioDataSource usuarioDataSource, RestauranteDataSource restauranteDataSource) {
        this.usuarioConverterController = new UsuarioConverterController(usuarioDataSource, restauranteDataSource);
    }

    @PatchMapping("/converter/{id}/{tipoDestino}")
    @Operation(summary = "Converte um usuário para outro tipo",
               description = "Converte um usuário existente entre os tipos disponíveis (CLIENTE, PROPRIETARIO)")
    @Transactional
    public ResponseEntity<UsuarioDTO> converterTipoUsuario(
            @Parameter(description = "ID do usuário a ser convertido")
            @PathVariable Long id,
            @Parameter(description = "Tipo de destino (CLIENTE, PROPRIETARIO)")
            @PathVariable NomeDoTipo tipoDestino) {

        logger.info("PATCH -> /api/v1/usuarios/converter/{}/{}", id, tipoDestino);

        UsuarioDTO usuarioConvertido = usuarioConverterController.converterTipoUsuario(id, tipoDestino);

        logger.info("Tipo do usuário atualizado com sucesso, ID={}, Novo Tipo={}",
                    usuarioConvertido.id(), usuarioConvertido.tipo());

        return ResponseEntity.ok(usuarioConvertido);
    }
}
