package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioGateway;

public class AtualizaSenhaProprietarioUseCase {
    final IProprietarioGateway proprietarioGateway;

    private AtualizaSenhaProprietarioUseCase(IProprietarioGateway proprietarioGateway) {
        this.proprietarioGateway = proprietarioGateway;
    }

    public static AtualizaSenhaProprietarioUseCase create(IProprietarioGateway proprietarioGateway) {
        return new AtualizaSenhaProprietarioUseCase(proprietarioGateway);
    }

    public Proprietario run(TrocaSenhaDTO trocaSenhaDTO) {
        final Proprietario proprietarioExistente = proprietarioGateway.buscaProprietarioPorLogin(trocaSenhaDTO.login())
                .orElseThrow(() -> new ProprietarioNotFoundException("Proprietário não encontrado com login: " + trocaSenhaDTO.login()));

        // Verificar se a senha atual está correta
        if (!proprietarioExistente.getSenha().equals(trocaSenhaDTO.senhaAtual())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        // Atualizar senha
        proprietarioExistente.setSenha(trocaSenhaDTO.novaSenha());

        return proprietarioGateway.atualizaProprietario(proprietarioExistente);
    }
}