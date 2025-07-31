package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.infrastructure.adapters.auth;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.AuthenticationServicePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityAuthenticationService implements AuthenticationServicePort {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityAuthenticationService(
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void authenticate(String login, String senha) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login, senha)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public String getCurrentUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Usuário não autenticado");
        }
        return authentication.getName();
    }

    @Override
    public void validateCurrentPassword(String login, String senha) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login, senha)
        );
        if (!authentication.isAuthenticated()) {
            throw new IllegalArgumentException("Senha atual inválida");
        }
    }

    @Override
    public void updatePassword(String login, String novaSenha) {
        String senhaEncriptada = passwordEncoder.encode(novaSenha);
        // TODO: Implementar atualização da senha no repositório
    }
}
