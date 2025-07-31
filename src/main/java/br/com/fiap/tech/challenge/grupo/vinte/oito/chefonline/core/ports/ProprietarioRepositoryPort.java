package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Proprietario;
import java.util.Optional;

public interface ProprietarioRepositoryPort {
    Proprietario salvar(Proprietario proprietario);
    Optional<Proprietario> buscarPorCnpj(String cnpj);
    boolean existePorCnpj(String cnpj);
}
