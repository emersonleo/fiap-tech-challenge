package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("SELECT c FROM UsuarioEntity c where id = :id AND tipo = :tipo")
    Optional<UsuarioEntity> findByIdWithTipo(@Param("id") Long id, @Param("tipo") NomeDoTipo tipo);

    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT c FROM UsuarioEntity c where email = :email AND tipo = :tipo")
    Optional<UsuarioEntity> findByEmailWithTipo(@Param("email") String email, @Param("tipo") NomeDoTipo tipo);

    Optional<UsuarioEntity> findByLogin(String login);
    @Query("SELECT c FROM UsuarioEntity c where login = :login AND tipo = :tipo")
    Optional<UsuarioEntity> findByLoginWithTipo(@Param("login") String login, @Param("tipo") NomeDoTipo tipo);

    @Query("SELECT c FROM UsuarioEntity c where login = :login AND senha = :senha AND tipo = :tipo")
    Optional<UsuarioEntity> findByLoginAndSenhaWithTipo(@Param("login") String login, @Param("senha") String senha, @Param("tipo") NomeDoTipo tipo);

    @Query("SELECT c FROM UsuarioEntity c where tipo = :tipo")
    Page<UsuarioEntity> findAllUsuariosWithTipo(Pageable pageable, @Param("tipo") NomeDoTipo tipo);

    @Query("DELETE FROM UsuarioEntity c where id = :id AND tipo = :tipo")
    Optional<UsuarioEntity> deleteByIdWithTipo(@Param("id") Long id, @Param("tipo") NomeDoTipo tipo);
}