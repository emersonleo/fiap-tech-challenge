package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.interface_.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CriarProprietarioRequest(
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

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos")
    String cnpj
) {}
