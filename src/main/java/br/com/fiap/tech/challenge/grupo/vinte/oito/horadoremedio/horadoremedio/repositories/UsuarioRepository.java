package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.repositories;

import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findById(Long id);
}