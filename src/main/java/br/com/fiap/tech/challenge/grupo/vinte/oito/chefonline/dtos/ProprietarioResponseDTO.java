package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dtos;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entities.Proprietario;

import java.time.LocalDateTime;

public record ProprietarioResponseDTO(
        Long id,
        String cnpj,
        String nome,
        String email,
        String login,
        String senha,
        String endereco,
        String razaoSocial,
        String nomeFantasia,
        LocalDateTime dataCriacaoRegistro,
        LocalDateTime dataUltimaAlteracaoRegistro
) {
    public ProprietarioResponseDTO(Proprietario proprietario) {
        this(
                proprietario.getId(),
                proprietario.getCnpj(),
                proprietario.getUsuario().getNome(),
                proprietario.getUsuario().getEmail(),
                proprietario.getUsuario().getLogin(),
                proprietario.getUsuario().getSenha(),
                proprietario.getUsuario().getEndereco(),
                proprietario.getRazaoSocial(),
                proprietario.getNomeFantasia(),
                proprietario.getUsuario().getDataCriacaoRegistro(),
                proprietario.getUsuario().getDataUltimaAlteracaoRegistro()
        );
    }
}
