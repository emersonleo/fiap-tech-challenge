package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.cardapio;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.restaurante.RestauranteEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.cardapio.ItemCardapioMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.restaurante.RestauranteMapper;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.cardapio.ItemCardapioJpaRepository;


@Component
public class ItemCardapioDataSource implements IItemCardapioDataSource{

    private final ItemCardapioJpaRepository repository;

    public ItemCardapioDataSource(ItemCardapioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemCardapio adicionaItemCardapio(ItemCardapio novoItemCardapio) {
        var itemCardapioEntity = ItemCardapioMapper.toEntity(novoItemCardapio);
        var savedItemCardapioEntity = repository.save(itemCardapioEntity);
        return ItemCardapioMapper.toDomain(savedItemCardapioEntity);
    }

    @Override
    public Optional<ItemCardapio> buscaItemCardapioPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaItemCardapioPorId'");
    }

    @Override
    public Optional<ItemCardapio> buscaTodosItensCardapio(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaTodosItensCardapio'");
    }

    @Override
    public Optional<ItemCardapio> buscaItensCardapioPorRestaurante(Long restauranteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscaItensCardapioPorRestaurante'");
    }

    @Override
    public ItemCardapio atualizaItemCardapio(ItemCardapio itemCardapio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizaItemCardapio'");
    }

    @Override
    public void deletaItemCardapio(ItemCardapio itemCardapio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletaItemCardapio'");
    }
    
}