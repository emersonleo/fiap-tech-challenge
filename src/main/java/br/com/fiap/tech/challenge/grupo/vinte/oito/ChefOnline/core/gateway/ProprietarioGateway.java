package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.IProprietarioGateway;

import java.util.List;
import java.util.Optional;

public class ProprietarioGateway implements IProprietarioGateway {
    private final IProprietarioDataSource proprietarioDataSource;

    private ProprietarioGateway(IProprietarioDataSource proprietarioDataSource) {
        this.proprietarioDataSource = proprietarioDataSource;
    }

    public static ProprietarioGateway create(IProprietarioDataSource proprietarioDataSource) {
       return new ProprietarioGateway(proprietarioDataSource);
    }

    @Override
    public Proprietario adicionaProprietario(Proprietario novoProprietario) {
        return proprietarioDataSource.adicionaProprietario(novoProprietario);
    }

    @Override
    public Proprietario buscaProprietarioPorEmail(String email) {
        return proprietarioDataSource.buscaProprietarioPorEmail(email);
    }

    @Override
    public Optional<Proprietario> buscaProprietarioPorId(Long id) {
        return proprietarioDataSource.buscaProprietarioPorId(id);
    }

    @Override
    public List<Proprietario> buscaTodosProprietarios(int page, int size) {
        return proprietarioDataSource.buscaTodosProprietarios(page, size);
    }

    @Override
    public Proprietario atualizaProprietario(Proprietario proprietario) {
        return proprietarioDataSource.atualizaProprietario(proprietario);
    }

    @Override
    public Optional<Proprietario> buscaProprietarioPorLogin(String login) {
        return proprietarioDataSource.buscaProprietarioPorLogin(login);
    }

    @Override
    public void deletaProprietario(Proprietario proprietario) {
        proprietarioDataSource.deletaProprietario(proprietario);
    }
}