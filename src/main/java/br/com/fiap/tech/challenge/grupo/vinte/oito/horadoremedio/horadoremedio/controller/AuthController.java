package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.controller;

import br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação JWT")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica usuário e retorna token JWT")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword())
            );
            
            log.info("Login realizado com sucesso para o usuário: {}", loginRequest.getUsername());
            
        } catch (BadCredentialsException e) {
            log.warn("Tentativa de login com credenciais inválidas para usuário: {}", 
                     loginRequest.getUsername());
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Credenciais inválidas"));
        }

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(loginRequest.getUsername());
        
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), 
                                                userDetails.getAuthorities().toString()));
    }

    // DTO Classes
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters e Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class JwtResponse {
        private String token;
        private String username;
        private String roles;

        public JwtResponse(String token, String username, String roles) {
            this.token = token;
            this.username = username;
            this.roles = roles;
        }

        // Getters
        public String getToken() { return token; }
        public String getUsername() { return username; }
        public String getRoles() { return roles; }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
    }
}