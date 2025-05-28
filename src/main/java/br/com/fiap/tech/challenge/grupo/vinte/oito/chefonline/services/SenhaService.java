package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaService {
    private final PasswordEncoder passwordEncoder;

    public SenhaService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hashSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean verificaSenha(String senha, String hashSenha) {
        return passwordEncoder.matches(senha, hashSenha);
    }
}
