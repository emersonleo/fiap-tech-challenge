package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import java.util.List;
import java.util.Optional;

public interface IProprietarioDataSource {
    Proprietario adicionaProprietario(Proprietario novoProprietario);
    Proprietario buscaProprietarioPorEmail(String email);
    Optional<Proprietario> buscaProprietarioPorId(Long id);
    List<Proprietario> buscaTodosProprietarios(int page, int size);
    Proprietario atualizaProprietario(Proprietario proprietario);
    Optional<Proprietario> buscaProprietarioPorLogin(String login);
    void deletaProprietario(Proprietario proprietario);
}
