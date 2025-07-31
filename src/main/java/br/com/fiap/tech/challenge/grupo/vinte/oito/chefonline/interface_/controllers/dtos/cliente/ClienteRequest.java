package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRequest(
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "Login é obrigatório")
    String login,

    @NotBlank(message = "Senha é obrigatória")
    String senha,

    @NotBlank(message = "Endereço é obrigatório")
    String endereco,

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
    String cpf
) {}
