package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.configs;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.*;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.*;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public LoginUseCase loginUseCase(AuthenticationServicePort authenticationService) {
        return new LoginUseCase(authenticationService);
    }

    @Bean
    public UpdatePasswordUseCase updatePasswordUseCase(AuthenticationServicePort authenticationService) {
        return new UpdatePasswordUseCase(authenticationService);
    }

    @Bean
    public CriarClienteUseCase criarClienteUseCase(
            ClienteRepositoryPort clienteRepository,
            UsuarioRepositoryPort usuarioRepository) {
        return new CriarClienteUseCase(clienteRepository, usuarioRepository);
    }

    @Bean
    public CriarProprietarioUseCase criarProprietarioUseCase(
            ProprietarioRepositoryPort proprietarioRepository,
            UsuarioRepositoryPort usuarioRepository) {
        return new CriarProprietarioUseCase(proprietarioRepository, usuarioRepository);
    }
}
