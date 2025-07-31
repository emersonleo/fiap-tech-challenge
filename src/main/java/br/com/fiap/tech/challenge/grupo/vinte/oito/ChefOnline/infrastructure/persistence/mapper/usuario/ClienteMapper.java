package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.ClienteEntity;

public class ClienteMapper {
    
    public static Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Cliente(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getLogin(),
            entity.getSenha(),
            entity.getEndereco()
        );
    }
    
    public static ClienteEntity toEntity(Cliente domain) {
        if (domain == null) {
            return null;
        }
        
        return new ClienteEntity(
            domain.getId(),
            domain.getNome(),
            domain.getEmail(),
            domain.getLogin(),
            domain.getSenha(),
            domain.getEndereco(),
            domain.getDataUltimaAlteracao()
        );
    }
    
    public static void updateEntityFromDomain(ClienteEntity entity, Cliente domain) {
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail());
        entity.setLogin(domain.getLogin());
        entity.setSenha(domain.getSenha());
        entity.setEndereco(domain.getEndereco());
        entity.setDataUltimaAlteracao(domain.getDataUltimaAlteracao());
    }
}