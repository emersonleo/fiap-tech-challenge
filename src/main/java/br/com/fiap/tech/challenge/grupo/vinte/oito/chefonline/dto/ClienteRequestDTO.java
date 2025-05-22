package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
public record ClienteRequestDTO(

        @NotBlank(message = "O campo 'nome' deve ser preenchido")
        @Size(min = 2, max = 70, message = "O nome deve ter entre 2 e 70 caracteres")
        String nome,

        @NotBlank(message = "O campo 'cpf' deve ser preenchido")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "O campo 'email' deve ser preenchido")
        @Email(message = "Digite um e-mail válido")
        String email,

        @NotBlank(message = "O campo 'login' deve ser preenchido")
        String login,

        @NotBlank(message = "O campo 'senha' deve ser preenchido")
        String senha,

        @NotBlank(message = "O campo 'endereço' deve ser preenchido")
        String endereco) {
}
