package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(authorize -> authorize
                // Endpoints públicos
                .requestMatchers("/auth/login", "/actuator/health", 
                               "/swagger-ui/**", "/api-docs/**", 
                               "/swagger-ui.html", "/v3/api-docs/**",
                               "/error", "/usuario/criar").permitAll()
                
                // Endpoints com roles específicas
                .requestMatchers("/medico/**").hasRole("MEDICO")
                .requestMatchers("/enfermeiro/**").hasRole("ENFERMEIRO")
                .requestMatchers("/paciente/**").hasRole("PACIENTE")
                
                // Qualquer outra requisição precisa estar autenticada
                .anyRequest().authenticated()
            );

        // Adicionar o filtro JWT
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}