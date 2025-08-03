package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByIdAndTipo(Long id, NomeDoTipo tipo);

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByEmailAndTipo(String email, NomeDoTipo tipo);

    Optional<UsuarioEntity> findByLogin(String login);

    Optional<UsuarioEntity> findByLoginAndTipo(String login, NomeDoTipo tipo);

    Optional<UsuarioEntity> findByLoginAndSenhaAndTipo(String login, String senha, NomeDoTipo tipo);

    Page<UsuarioEntity> findAllUsuariosByTipo(Pageable pageable, NomeDoTipo tipo);

    void deleteByIdAndTipo(Long id, NomeDoTipo tipo);
}