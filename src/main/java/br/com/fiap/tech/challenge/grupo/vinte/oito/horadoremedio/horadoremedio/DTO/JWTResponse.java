package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTResponse {
    private String token;
    private String username;
    private String roles;

    public JWTResponse(String token, String username, String roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    // Getters
    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getRoles() { return roles; }
}