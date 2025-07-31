package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteGateway {
    Cliente adicionaCliente(Cliente novoCliente);
    Cliente buscaClientePorEmail(String email);
    Optional<Cliente> buscaClientePorId(Long id);
    List<Cliente> buscaTodosClientes(int page, int size);
    Cliente atualizaCliente(Cliente cliente);
    Optional<Cliente> buscaClientePorLogin(String login);
    void deletaCliente(Cliente cliente);
}
