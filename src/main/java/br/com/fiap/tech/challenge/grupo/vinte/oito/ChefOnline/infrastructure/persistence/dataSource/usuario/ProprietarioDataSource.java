package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.ProprietarioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.ProprietarioMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.ProprietarioJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProprietarioDataSource implements IProprietarioDataSource {
    
    private final ProprietarioJpaRepository repository;
    
    public ProprietarioDataSource(ProprietarioJpaRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Proprietario adicionaProprietario(Proprietario novoProprietario) {
        ProprietarioEntity entity = ProprietarioMapper.toEntity(novoProprietario);
        ProprietarioEntity savedEntity = repository.save(entity);
        return ProprietarioMapper.toDomain(savedEntity);
    }
    
    @Override
    public Proprietario buscaProprietarioPorEmail(String email) {
        Optional<ProprietarioEntity> entity = repository.findByEmail(email);
        return entity.map(ProprietarioMapper::toDomain).orElse(null);
    }
    
    @Override
    public Optional<Proprietario> buscaProprietarioPorId(Long id) {
        Optional<ProprietarioEntity> entity = repository.findById(id);
        return entity.map(ProprietarioMapper::toDomain);
    }
    
    @Override
    public List<Proprietario> buscaTodosProprietarios(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAllProprietarios(pageRequest)
                .getContent()
                .stream()
                .map(ProprietarioMapper::toDomain)
                .toList();
    }
    
    @Override
    public Proprietario atualizaProprietario(Proprietario proprietario) {
        Optional<ProprietarioEntity> existingEntity = repository.findById(proprietario.getId());
        
        if (existingEntity.isPresent()) {
            ProprietarioEntity entity = existingEntity.get();
            ProprietarioMapper.updateEntityFromDomain(entity, proprietario);
            ProprietarioEntity savedEntity = repository.save(entity);
            return ProprietarioMapper.toDomain(savedEntity);
        }
        
        return null;
    }
    
    @Override
    public Optional<Proprietario> buscaProprietarioPorLogin(String login) {
        Optional<ProprietarioEntity> entity = repository.findByLogin(login);
        return entity.map(ProprietarioMapper::toDomain);
    }
    
    @Override
    public void deletaProprietario(Proprietario proprietario) {
        if (proprietario.getId() != null) {
            repository.deleteById(proprietario.getId());
        }
    }
}