package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    
    Optional<ClienteEntity> findByEmail(String email);
    
    Optional<ClienteEntity> findByLogin(String login);
    
    @Query("SELECT c FROM ClienteEntity c")
    Page<ClienteEntity> findAllClientes(Pageable pageable);
    
    @Query("SELECT COUNT(c) > 0 FROM ClienteEntity c WHERE c.email = :email AND c.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
    
    @Query("SELECT COUNT(c) > 0 FROM ClienteEntity c WHERE c.login = :login AND c.id != :id")
    boolean existsByLoginAndIdNot(@Param("login") String login, @Param("id") Long id);
}