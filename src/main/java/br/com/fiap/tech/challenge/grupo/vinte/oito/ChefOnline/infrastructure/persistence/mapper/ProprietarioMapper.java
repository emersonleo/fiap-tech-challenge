package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.ProprietarioEntity;

public class ProprietarioMapper {
    
    public static Proprietario toDomain(ProprietarioEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Proprietario(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getLogin(),
            entity.getSenha(),
            entity.getEndereco()
        );
    }
    
    public static ProprietarioEntity toEntity(Proprietario domain) {
        if (domain == null) {
            return null;
        }
        
        return new ProprietarioEntity(
            domain.getId(),
            domain.getNome(),
            domain.getEmail(),
            domain.getLogin(),
            domain.getSenha(),
            domain.getEndereco(),
            domain.getDataUltimaAlteracao()
        );
    }
    
    public static void updateEntityFromDomain(ProprietarioEntity entity, Proprietario domain) {
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail());
        entity.setLogin(domain.getLogin());
        entity.setSenha(domain.getSenha());
        entity.setEndereco(domain.getEndereco());
        entity.setDataUltimaAlteracao(domain.getDataUltimaAlteracao());
    }
}