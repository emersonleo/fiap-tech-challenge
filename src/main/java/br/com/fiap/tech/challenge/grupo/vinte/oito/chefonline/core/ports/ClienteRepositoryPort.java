package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Cliente;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorCpf(String cpf);
    boolean existePorCpf(String cpf);
}
