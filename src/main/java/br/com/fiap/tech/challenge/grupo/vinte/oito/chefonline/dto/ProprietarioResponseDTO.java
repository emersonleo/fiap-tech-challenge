package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Proprietario;

import java.time.LocalDate;

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
        LocalDate dataCriacaoRegistro,
        LocalDate dataUltimaAlteracaoRegistro
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
