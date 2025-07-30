package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NovoProprietarioDTO(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O formato do email é inválido")
        String email,

        @NotBlank(message = "O login é obrigatório")
        String login,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        String endereco
) {
}