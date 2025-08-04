package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.cardapio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.cardapio.ItemCardapio;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.cardapio.IItemCardapioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.cardapio.ItemCardapioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.cardapio.ItemCardapioMapper;
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
        Optional<ItemCardapioEntity> itemCardapioEntity = repository.findById(id);
        return itemCardapioEntity.map(ItemCardapioMapper::toDomain);
    }

    @Override
    public List<ItemCardapio> buscaTodosItensCardapio(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = repository.findAll(pageable);
        return pageResult.getContent()
                .stream()
                .map(ItemCardapioMapper::toDomain)
                .toList();
    }

    @Override
    public List<ItemCardapio> buscaItensCardapioPorRestaurante(Long restauranteId) {
        List<ItemCardapioEntity> itemCardapioEntity = repository.findAllByRestauranteId(restauranteId);
            return itemCardapioEntity.stream() 
            .map(ItemCardapioMapper::toDomain)
            .toList();  
    }

    @Override
    public void atualizaItemCardapio(ItemCardapio itemCardapio) {
        Optional<ItemCardapioEntity> itemCardapioExistente = repository.findById(itemCardapio.getId());

        if(itemCardapioExistente.isPresent()){
            ItemCardapioEntity entity = itemCardapioExistente.get();
            ItemCardapioMapper.updateEntityFromDomain(entity, itemCardapio);
            repository.save(entity);
        }
    }

    @Override
    public void deletaItemCardapio(ItemCardapio itemCardapio) {
        if (itemCardapio.getId() != null) {
            repository.deleteById(itemCardapio.getId());
        }
    }
    
}