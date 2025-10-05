package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.DTO.CriaUsuarioRequest;
import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Endpoints para gerenciamento de usuários")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody CriaUsuarioRequest usuario) {
        log.info("Criando usuário: {}", usuario);
        
        if(usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null || usuario.getUsername() == null) {
            throw new IllegalArgumentException("Nome, email, username e senha são obrigatórios");
        }
        
        try{
            Usuario NovoUsuario = usuarioService.criarUsuario(usuario);     
            return ResponseEntity.status(HttpStatus.CREATED).body(NovoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
