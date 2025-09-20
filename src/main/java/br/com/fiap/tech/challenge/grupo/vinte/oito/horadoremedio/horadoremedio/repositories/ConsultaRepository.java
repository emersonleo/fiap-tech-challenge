package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.repositories;

import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Consulta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
   Optional<Consulta> findByMedicoId(Long medicoId);
   Optional<Consulta> findByPacienteId(Long pacienteId);
}