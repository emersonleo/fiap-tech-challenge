package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.ClienteRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {
    private final ClienteJpaRepository jpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = toEntity(cliente);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return Optional.ofNullable(jpaRepository.findByCpf(cpf))
                .map(this::toDomain);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    private ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setNome(cliente.getNome());
        entity.setEmail(cliente.getEmail());
        entity.setLogin(cliente.getLogin());
        entity.setSenha(cliente.getSenha());
        entity.setEndereco(cliente.getEndereco());
        entity.setCpf(cliente.getCpf());
        entity.setDataCriacaoRegistro(cliente.getDataCriacaoRegistro());
        entity.setDataUltimaAlteracaoRegistro(cliente.getDataUltimaAlteracaoRegistro());
        return entity;
    }

    private Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
            entity.getNome(),
            entity.getEmail(),
            entity.getLogin(),
            entity.getSenha(),
            entity.getEndereco(),
            entity.getCpf()
        );
    }
}
