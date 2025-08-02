package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.UsuarioMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.UsuarioJpaRepository;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProprietarioDataSource implements IProprietarioDataSource {
    
    private final UsuarioJpaRepository repository;

    private static Proprietario safeCastProprietario(Usuario usuario) {
        return usuario instanceof Proprietario proprietario ? proprietario : null;
    }

    public ProprietarioDataSource(UsuarioJpaRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Proprietario adicionaProprietario(Proprietario novoProprietario) {
        UsuarioEntity entity = UsuarioMapper.toEntity(novoProprietario);
        UsuarioEntity savedEntity = repository.save(entity);
        return safeCastProprietario(UsuarioMapper.toDomain(savedEntity));
    }
    
    @Override
    public Optional<Proprietario> buscaProprietarioPorEmail(String email) {
        Optional<UsuarioEntity> entity = repository.findByEmailWithTipo(email, NomeDoTipo.PROPRIETARIO);
        return entity.map(UsuarioMapper::toDomain).map(ProprietarioDataSource::safeCastProprietario);
    }
    
    @Override
    public Optional<Proprietario> buscaProprietarioPorId(Long id) {
        Optional<UsuarioEntity> entity = repository.findByIdWithTipo(id, NomeDoTipo.PROPRIETARIO);
        return entity.map(UsuarioMapper::toDomain).map(ProprietarioDataSource::safeCastProprietario);
    }
    
    @Override
    public List<Proprietario> buscaTodosProprietarios(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAllUsuariosWithTipo(pageRequest, NomeDoTipo.PROPRIETARIO)
                .getContent()
                .stream()
                .map(UsuarioMapper::toDomain)
                .map(ProprietarioDataSource::safeCastProprietario)
                .toList();
    }
    
    @Override
    public Proprietario atualizaProprietario(Proprietario proprietario) {
        Optional<UsuarioEntity> existingEntity = repository.findByIdWithTipo(proprietario.getId(), NomeDoTipo.PROPRIETARIO);
        
        if (existingEntity.isPresent()) {
            UsuarioEntity entity = existingEntity.get();
            UsuarioMapper.updateEntityFromDomain(entity, proprietario);
            UsuarioEntity savedEntity = repository.save(entity);
            return safeCastProprietario(UsuarioMapper.toDomain(savedEntity));
        }
        
        throw new ProprietarioNotFoundException(proprietario.getId());
    }
    
    @Override
    public Optional<Proprietario> buscaProprietarioPorLogin(String login) {
        Optional<UsuarioEntity> entity = repository.findByLoginWithTipo(login, NomeDoTipo.PROPRIETARIO);
        return entity.map(UsuarioMapper::toDomain).map(ProprietarioDataSource::safeCastProprietario);
    }
    
    @Override
    public void deletaProprietario(Proprietario proprietario) {
        if (proprietario.getId() != null) {
            repository.deleteByIdWithTipo(proprietario.getId(), NomeDoTipo.PROPRIETARIO);
        }
    }
}