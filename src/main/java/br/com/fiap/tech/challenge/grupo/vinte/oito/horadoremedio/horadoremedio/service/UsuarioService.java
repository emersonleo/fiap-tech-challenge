package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.service;


import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.DTO.CriaUsuarioRequest;
import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.repositories.UsuarioRepository;
import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.security.SecurityConfig;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(CriaUsuarioRequest usuario) {
        
        SecurityConfig securityConfig = new SecurityConfig();

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setSenha(securityConfig.passwordEncoder().encode(usuario.getSenha()));
        novoUsuario.setUsername(usuario.getUsername());
        novoUsuario.setRoles(usuario.getRoles());

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if(usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username já cadastrado");
        }

        try{        
            return usuarioRepository.save(novoUsuario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage());
        }

    }

    public Usuario buscarUsuarioPorId(Long id) {
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        return usuarioRepository.findById(id).orElse(null);
    }
}
