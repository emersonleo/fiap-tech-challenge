package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.proprietario.IProprietarioAuthDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioAuthDataSource implements IProprietarioAuthDataSource {
    private final UsuarioJpaRepository usuarioJpaRepository;

    private static Proprietario safeCastProprietario(Usuario usuario) {
        return usuario instanceof Proprietario proprietario ? proprietario : null;
    }

    public ProprietarioAuthDataSource(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public boolean verificaCredenciais(VerificaCredenciaisDTO verificaCredenciaisDTO) {
        return usuarioJpaRepository
                .findByLoginAndSenhaAndTipo(
                        verificaCredenciaisDTO.login(),
                        verificaCredenciaisDTO.senha(),
                        NomeDoTipo.PROPRIETARIO
                ).isPresent();
    }
}
