import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.repositories;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
   findByMedicoId(Long medicoId);
   findByPacienteId(Long pacienteId);
}