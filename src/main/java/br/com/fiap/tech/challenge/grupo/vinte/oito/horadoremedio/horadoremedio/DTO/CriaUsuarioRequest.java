package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.DTO;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriaUsuarioRequest {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email não pode ser vazio")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    private String senha;

    @NotBlank(message = "Username não pode ser vazio")
    private String username;

    @NotBlank(message = "Roles não pode ser vazio")
    @Size(min = 1, message = "Deve ter pelo menos uma role")
    private Set<String> roles; 

}
