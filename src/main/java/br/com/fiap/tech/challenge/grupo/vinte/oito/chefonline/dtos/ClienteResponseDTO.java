package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dtos;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entities.Cliente;

import java.time.LocalDateTime;

public record ClienteResponseDTO(
        Long id,
        String cpf,
        String nome,
        String email,
        String login,
        String senha,
        String endereco,
        LocalDateTime dataCriacaoRegistro,
        LocalDateTime dataUltimaAlteracaoRegistro
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getCpf(),
                cliente.getUsuario().getNome(),
                cliente.getUsuario().getEmail(),
                cliente.getUsuario().getLogin(),
                cliente.getUsuario().getSenha(),
                cliente.getUsuario().getEndereco(),
                cliente.getUsuario().getDataCriacaoRegistro(),
                cliente.getUsuario().getDataUltimaAlteracaoRegistro()
        );
    }
}