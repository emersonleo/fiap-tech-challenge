package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.auth;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.UsuarioRepositoryPort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepositoryPort usuarioRepository;

    public CustomUserDetailsService(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.buscarPorLogin(login)
            .map(usuario -> User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles("USER")
                .build())
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
    }
}
