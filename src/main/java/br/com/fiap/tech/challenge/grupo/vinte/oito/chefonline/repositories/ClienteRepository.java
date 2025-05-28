package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repositories;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAll(Pageable pageable);
    Optional<Cliente> findByUsuarioLogin(String login);
}
