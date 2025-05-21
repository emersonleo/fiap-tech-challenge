package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Proprietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    Page<Proprietario> findAll(Pageable pageable);
    Optional<Proprietario> findByLogin(String login);
}
