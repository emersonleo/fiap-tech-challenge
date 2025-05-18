package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UsuarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void createUser(UsuarioDTO usuarioDTO) {
        var save = usuarioRepository.save(new Usuario(usuarioDTO));
        Assert.state(save == 1, "Erro ao criar usuario: " + usuarioDTO.nome());
    }

    public List<Usuario> findAllUsers(int page, int size) {
        int offset = (page - 1) * size;
        return usuarioRepository.findAllUsers(size, offset);
    }

}
