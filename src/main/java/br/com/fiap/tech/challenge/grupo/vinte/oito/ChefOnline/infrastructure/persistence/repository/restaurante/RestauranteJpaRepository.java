package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.restaurante.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {
}