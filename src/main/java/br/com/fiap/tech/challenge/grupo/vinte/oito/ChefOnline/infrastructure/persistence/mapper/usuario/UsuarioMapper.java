package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.mapper.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;

public class UsuarioMapper {

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }

        return switch (entity.getTipo()) {
            case CLIENTE -> new Cliente(
                    entity.getId(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getLogin(),
                    entity.getSenha(),
                    entity.getEndereco(),
                    entity.getDataUltimaAlteracao()
            );
            case PROPRIETARIO -> new Proprietario(
                    entity.getId(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getLogin(),
                    entity.getSenha(),
                    entity.getEndereco(),
                    entity.getDataUltimaAlteracao()
            );
        };
    }

    public static UsuarioEntity toEntity(Usuario domain) {
        if (domain == null) {
            return null;
        }

        return new UsuarioEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getLogin(),
                domain.getSenha(),
                domain.getEndereco(),
                domain.getTipo(),
                domain.getDataUltimaAlteracao()
        );
    }

    public static void updateEntityFromDomain(UsuarioEntity entity, Usuario domain) {
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail());
        entity.setLogin(domain.getLogin());
        entity.setSenha(domain.getSenha());
        entity.setEndereco(domain.getEndereco());
        entity.setTipo(domain.getTipo());
        entity.setDataUltimaAlteracao(domain.getDataUltimaAlteracao());
    }
}