package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${app.security.medico.username:medico}")
    private String medicoUsername;
    
    @Value("${app.security.medico.password:MedicoSeguro@2024}")
    private String medicoPassword;
    
    @Value("${app.security.enfermeiro.username:enfermeiro}")
    private String enfermeiroUsername;
    
    @Value("${app.security.enfermeiro.password:EnfermeiroSeguro@2024}")
    private String enfermeiroPassword;
    
    @Value("${app.security.paciente.username:paciente}")
    private String pacienteUsername;
    
    @Value("${app.security.paciente.password:PacienteSeguro@2024}")
    private String pacientePassword;

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
                               "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                
                // Endpoints com roles específicas
                .requestMatchers("/medicos/**").hasRole("MEDICO")
                .requestMatchers("/enfermeiros/**").hasRole("ENFERMEIRO")
                .requestMatchers("/pacientes/**").hasRole("PACIENTE")
                
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
    @Profile({"dev", "test"})
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        
        UserDetails medico = User.withUsername(medicoUsername)
            .password(passwordEncoder.encode(medicoPassword))
            .roles("MEDICO")
            .build();
            
        UserDetails enfermeiro = User.withUsername(enfermeiroUsername)
            .password(passwordEncoder.encode(enfermeiroPassword))
            .roles("ENFERMEIRO")
            .build();
            
        UserDetails paciente = User.withUsername(pacienteUsername)
            .password(passwordEncoder.encode(pacientePassword))
            .roles("PACIENTE")
            .build();
            
        return new InMemoryUserDetailsManager(medico, enfermeiro, paciente);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}