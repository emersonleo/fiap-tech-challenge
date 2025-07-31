package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {
    ClienteEntity findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
