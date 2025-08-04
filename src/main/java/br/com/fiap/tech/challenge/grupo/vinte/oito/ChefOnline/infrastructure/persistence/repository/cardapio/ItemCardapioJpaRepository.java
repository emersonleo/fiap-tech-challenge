package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.cardapio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.cardapio.ItemCardapioEntity;

@Repository
public interface ItemCardapioJpaRepository extends JpaRepository<ItemCardapioEntity, Long> {

    Page<ItemCardapioEntity> findAll(Pageable pageable);

    List<ItemCardapioEntity> findAllByRestauranteId(Long restauranteId);
    
}