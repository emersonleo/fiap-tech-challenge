package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProprietarioRequestDTO(
        
        @NotBlank(message = "O campo 'nome' deve ser preenchido")
        @Size(min = 2, max = 70, message = "O nome deve ter entre 2 e 70 caracteres")
        String nome,

        @NotBlank(message = "O campo 'email' deve ser preenchido")
        @Email(message = "Digite um e-mail valido")
        String email,

        @NotBlank(message = "O campo 'login' deve ser preenchido")
        String login,

        @NotBlank(message = "O campo 'senha' deve ser preenchido")
        String senha,

        @NotBlank(message = "O campo 'endereco' deve ser preenchido")
        String endereco,

        @NotBlank(message = "O campo 'cnpj' deve ser preenchido")
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 dígitos numericos")
        String cnpj,

        @NotBlank(message = "O campo 'razão social' deve ser preenchido")
        @Size(min = 5, max = 70, message = "A razao social deve ter entre 5 e 70 caracteres")
        String razaoSocial,

        @NotBlank(message = "O campo 'nome fantasia' deve ser preenchido")
        @Size(min = 1, max = 70, message = "O nome fantasia deve ter entre 1 e 70 caracteres")
        String nomeFantasia) {
}
