package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.UsuarioMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.UsuarioJpaRepository;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.usuario.UsuarioNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioDataSource implements IUsuarioDataSource {

    private final UsuarioJpaRepository repository;

    public UsuarioDataSource(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario adicionaUsuario(Usuario novoUsuario) {
        UsuarioEntity entity = UsuarioMapper.toEntity(novoUsuario);
        UsuarioEntity savedEntity = repository.save(entity);
        return UsuarioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorEmail(String email) {
        Optional<UsuarioEntity> entity = repository.findByEmail(email);
        return entity.map(UsuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorId(Long id) {
        Optional<UsuarioEntity> entity = repository.findById(id);
        return entity.map(UsuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> buscaTodosUsuarios(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(UsuarioMapper::toDomain)

                .toList();
    }

    @Override
    public Usuario atualizaUsuario(Usuario usuario) {
        Optional<UsuarioEntity> existingEntity = repository.findById(usuario.getId());

        if (existingEntity.isPresent()) {
            UsuarioEntity entity = existingEntity.get();
            UsuarioMapper.updateEntityFromDomain(entity, usuario);
            UsuarioEntity savedEntity = repository.save(entity);
            return UsuarioMapper.toDomain(savedEntity);
        }

        throw new UsuarioNotFoundException(usuario.getId());
    }

    @Override
    public Optional<Usuario> buscaUsuarioPorLogin(String login) {
        Optional<UsuarioEntity> entity = repository.findByLogin(login);
        return entity.map(UsuarioMapper::toDomain);
    }

    @Override
    public void deletaUsuario(Usuario usuario) {
        if (usuario.getId() != null) {
            repository.deleteById(usuario.getId());
        }
    }
}