package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.ProprietarioRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProprietarioRepositoryAdapter implements ProprietarioRepositoryPort {
    private final ProprietarioJpaRepository jpaRepository;

    public ProprietarioRepositoryAdapter(ProprietarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Proprietario salvar(Proprietario proprietario) {
        ProprietarioEntity entity = toEntity(proprietario);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<Proprietario> buscarPorCnpj(String cnpj) {
        return Optional.ofNullable(jpaRepository.findByCnpj(cnpj))
                .map(this::toDomain);
    }

    @Override
    public boolean existePorCnpj(String cnpj) {
        return jpaRepository.existsByCnpj(cnpj);
    }

    private ProprietarioEntity toEntity(Proprietario proprietario) {
        ProprietarioEntity entity = new ProprietarioEntity();
        entity.setNome(proprietario.getNome());
        entity.setEmail(proprietario.getEmail());
        entity.setLogin(proprietario.getLogin());
        entity.setSenha(proprietario.getSenha());
        entity.setEndereco(proprietario.getEndereco());
        entity.setCnpj(proprietario.getCnpj());
        entity.setDataCriacaoRegistro(proprietario.getDataCriacaoRegistro());
        entity.setDataUltimaAlteracaoRegistro(proprietario.getDataUltimaAlteracaoRegistro());
        return entity;
    }

    private Proprietario toDomain(ProprietarioEntity entity) {
        return new Proprietario(
            entity.getNome(),
            entity.getEmail(),
            entity.getLogin(),
            entity.getSenha(),
            entity.getEndereco(),
            entity.getCnpj()
        );
    }
}
