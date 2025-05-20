package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAll(Pageable pageable);

}
