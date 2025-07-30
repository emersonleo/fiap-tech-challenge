package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.ProprietarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProprietarioJpaRepository extends JpaRepository<ProprietarioEntity, Long> {
    
    Optional<ProprietarioEntity> findByEmail(String email);
    
    Optional<ProprietarioEntity> findByLogin(String login);
    
    @Query("SELECT p FROM ProprietarioEntity p")
    Page<ProprietarioEntity> findAllProprietarios(Pageable pageable);
    
    @Query("SELECT COUNT(p) > 0 FROM ProprietarioEntity p WHERE p.email = :email AND p.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
    
    @Query("SELECT COUNT(p) > 0 FROM ProprietarioEntity p WHERE p.login = :login AND p.id != :id")
    boolean existsByLoginAndIdNot(@Param("login") String login, @Param("id") Long id);
}