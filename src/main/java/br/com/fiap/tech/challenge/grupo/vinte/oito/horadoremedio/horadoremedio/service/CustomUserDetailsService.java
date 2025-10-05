package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.entities.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuário não encontrado: " + username
            ));

        Collection<GrantedAuthority> authorities = usuario.getRoles().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());


        return new User(
            usuario.getUsername(),
            usuario.getSenha(),      
            true,                     
            true,                     
            true,                     
            true,                     
            authorities
        );
    }
}