package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario.UsuarioMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.UsuarioJpaRepository;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.cliente.ClienteNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteDataSource implements IClienteDataSource {

    private final UsuarioJpaRepository repository;

    private static Cliente safeCastCliente(Usuario usuario) {
        return usuario instanceof Cliente cliente ? cliente : null;
    }

    public ClienteDataSource(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente adicionaCliente(Cliente novoCliente) {
        UsuarioEntity entity = UsuarioMapper.toEntity(novoCliente);
        UsuarioEntity savedEntity = repository.save(entity);
        return safeCastCliente(UsuarioMapper.toDomain(savedEntity));
    }

    @Override
    public Optional<Cliente> buscaClientePorEmail(String email) {
        Optional<UsuarioEntity> entity = repository.findByEmailWithTipo(email, NomeDoTipo.CLIENTE);
        return entity.map(UsuarioMapper::toDomain).map(ClienteDataSource::safeCastCliente);
    }

    @Override
    public Optional<Cliente> buscaClientePorId(Long id) {
        Optional<UsuarioEntity> entity = repository.findByIdWithTipo(id, NomeDoTipo.CLIENTE);
        return entity.map(UsuarioMapper::toDomain).map(ClienteDataSource::safeCastCliente);
    }

    @Override
    public List<Cliente> buscaTodosClientes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAllUsuariosWithTipo(pageRequest, NomeDoTipo.CLIENTE)
                .getContent()
                .stream()
                .map(UsuarioMapper::toDomain)
                .map(ClienteDataSource::safeCastCliente)
                .toList();
    }

    @Override
    public Cliente atualizaCliente(Cliente cliente) {
        Optional<UsuarioEntity> existingEntity = repository.findByIdWithTipo(cliente.getId(), NomeDoTipo.CLIENTE);

        if (existingEntity.isPresent()) {
            UsuarioEntity entity = existingEntity.get();
            UsuarioMapper.updateEntityFromDomain(entity, cliente);
            UsuarioEntity savedEntity = repository.save(entity);
            return safeCastCliente(UsuarioMapper.toDomain(savedEntity));
        }

        throw new ClienteNotFoundException(cliente.getId());
    }

    @Override
    public Optional<Cliente> buscaClientePorLogin(String login) {
        Optional<UsuarioEntity> entity = repository.findByLoginWithTipo(login, NomeDoTipo.CLIENTE);
        return entity.map(UsuarioMapper::toDomain).map(ClienteDataSource::safeCastCliente);
    }

    @Override
    public void deletaCliente(Cliente cliente) {
        if (cliente.getId() != null) {
            repository.deleteByIdWithTipo(cliente.getId(), NomeDoTipo.CLIENTE);
        }
    }
}