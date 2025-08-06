package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.auth.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.VerificaCredenciaisDTO;

public interface IProprietarioAuthDataSource {
    boolean verificaCredenciais(VerificaCredenciaisDTO verificaCredenciaisDTO);
}
