package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.ClienteEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.ClienteMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.ClienteJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteDataSource implements IClienteDataSource {
    
    private final ClienteJpaRepository repository;
    
    public ClienteDataSource(ClienteJpaRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Cliente adicionaCliente(Cliente novoCliente) {
        ClienteEntity entity = ClienteMapper.toEntity(novoCliente);
        ClienteEntity savedEntity = repository.save(entity);
        return ClienteMapper.toDomain(savedEntity);
    }
    
    @Override
    public Cliente buscaClientePorEmail(String email) {
        Optional<ClienteEntity> entity = repository.findByEmail(email);
        return entity.map(ClienteMapper::toDomain).orElse(null);
    }
    
    @Override
    public Optional<Cliente> buscaClientePorId(Long id) {
        Optional<ClienteEntity> entity = repository.findById(id);
        return entity.map(ClienteMapper::toDomain);
    }
    
    @Override
    public List<Cliente> buscaTodosClientes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAllClientes(pageRequest)
                .getContent()
                .stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }
    
    @Override
    public Cliente atualizaCliente(Cliente cliente) {
        Optional<ClienteEntity> existingEntity = repository.findById(cliente.getId());
        
        if (existingEntity.isPresent()) {
            ClienteEntity entity = existingEntity.get();
            ClienteMapper.updateEntityFromDomain(entity, cliente);
            ClienteEntity savedEntity = repository.save(entity);
            return ClienteMapper.toDomain(savedEntity);
        }
        
        return null;
    }
    
    @Override
    public Optional<Cliente> buscaClientePorLogin(String login) {
        Optional<ClienteEntity> entity = repository.findByLogin(login);
        return entity.map(ClienteMapper::toDomain);
    }
    
    @Override
    public void deletaCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            repository.deleteById(cliente.getId());
        }
    }
}