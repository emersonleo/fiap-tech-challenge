package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.UsuarioRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return Optional.ofNullable(jpaRepository.findByEmail(email))
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return jpaRepository.findById(login)
                .map(this::toDomain);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(String login) {
        jpaRepository.deleteById(login);
    }

    private UsuarioEntity toEntity(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setLogin(usuario.getLogin());
        entity.setSenha(usuario.getSenha());
        entity.setEndereco(usuario.getEndereco());
        entity.setDataCriacaoRegistro(usuario.getDataCriacaoRegistro());
        entity.setDataUltimaAlteracaoRegistro(usuario.getDataUltimaAlteracaoRegistro());
        return entity;
    }

    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
            entity.getNome(),
            entity.getEmail(),
            entity.getLogin(),
            entity.getSenha(),
            entity.getEndereco()
        );
    }
}
