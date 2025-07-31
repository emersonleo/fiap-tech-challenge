package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.usecases;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.domain.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.ClienteRepositoryPort;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports.UsuarioRepositoryPort;

public class CriarClienteUseCase {
    private final ClienteRepositoryPort clienteRepository;
    private final UsuarioRepositoryPort usuarioRepository;

    public CriarClienteUseCase(ClienteRepositoryPort clienteRepository, UsuarioRepositoryPort usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Cliente executar(String nome, String email, String login, String senha, String endereco, String cpf) {
        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if (usuarioRepository.buscarPorLogin(login).isPresent()) {
            throw new IllegalArgumentException("Login já cadastrado");
        }
        if (clienteRepository.existePorCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Cliente cliente = new Cliente(nome, email, login, senha, endereco, cpf);
        return clienteRepository.salvar(cliente);
    }
}
