import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.repositories;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    findByEmail(String email);
}