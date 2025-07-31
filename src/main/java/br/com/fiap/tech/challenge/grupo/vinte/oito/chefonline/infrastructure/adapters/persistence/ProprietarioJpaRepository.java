package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioJpaRepository extends JpaRepository<ProprietarioEntity, String> {
    ProprietarioEntity findByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
}
