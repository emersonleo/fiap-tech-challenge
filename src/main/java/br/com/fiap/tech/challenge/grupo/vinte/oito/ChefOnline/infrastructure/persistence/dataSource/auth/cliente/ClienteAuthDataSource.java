package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.auth.cliente;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.auth.cliente.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.cliente.IClienteAuthDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteAuthDataSource implements IClienteAuthDataSource {
    private final UsuarioJpaRepository usuarioJpaRepository;

    private static Cliente safeCastCliente(Usuario usuario) {
        return usuario instanceof Cliente cliente ? cliente : null;
    }

    public ClienteAuthDataSource(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public boolean verificaCredenciais(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        return usuarioJpaRepository
                .findByLoginAndSenhaWithTipo(
                        verificaCredenciaisDTO.login(),
                        verificaCredenciaisDTO.senha(),
                        NomeDoTipo.CLIENTE
                ).isPresent();
    }
}
