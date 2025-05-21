package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository.ClienteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final SenhaService senhaService;

    public ClienteService(ClienteRepository clienteRepository, SenhaService senhaService) {
        this.clienteRepository = clienteRepository;
        this.senhaService = senhaService;
    }

    public void criaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO);
        cliente.setSenha(senhaService.hashSenha(cliente.getSenha()));
        cliente.setDataCriacaoRegistro(LocalDate.now());
        var save = clienteRepository.save(cliente);
//        Assert.state(save == 1, "Erro ao criar usuario: " + clienteDTO.nome());
    }

    public List<Cliente> buscaTodosClientes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = clienteRepository.findAll(pageable);
        return pageResult.getContent();
    }

    public Optional<Cliente> buscaClientPorLogin(String login) {
        return clienteRepository.findByLogin(login);
    }

    public void atualizaCliente(ClienteDTO clienteDTO, Long id) {
        var clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        clienteExistente.setNome(clienteDTO.nome());
        clienteExistente.setEmail(clienteDTO.email());
        clienteExistente.setLogin(clienteDTO.login());
        clienteExistente.setSenha(clienteDTO.senha());
        clienteExistente.setEndereco(clienteDTO.endereco());
        clienteExistente.setDataUltimaAlteracaoRegistro(LocalDate.now());

        clienteRepository.save(clienteExistente);
    }

    public void deletaCliente(Long id) {
        var clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        clienteRepository.delete(clienteExistente);
    }

}
