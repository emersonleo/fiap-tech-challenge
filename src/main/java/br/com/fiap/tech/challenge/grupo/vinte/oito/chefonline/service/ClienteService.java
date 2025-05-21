package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteResponseDTO;
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

    public void criaCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente(clienteRequestDTO);
        cliente.getUsuario().setSenha(senhaService.hashSenha(cliente.getUsuario().getSenha()));
        cliente.getUsuario().setDataCriacaoRegistro(LocalDate.now());
        var save = clienteRepository.save(cliente);
//        Assert.state(save == 1, "Erro ao criar usuario: " + clienteRequestDTO.nome());
    }

    public List<ClienteResponseDTO> buscaTodosClientes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = clienteRepository.findAll(pageable);
        return pageResult.getContent()
                .stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }

    public Optional<Cliente> buscaClientPorLogin(String login) {
        return clienteRepository.findByUsuarioLogin(login);
    }

    public void atualizaCliente(ClienteRequestDTO clienteRequestDTO, Long id) {
        var clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        clienteExistente.getUsuario().setNome(clienteRequestDTO.nome());
        clienteExistente.getUsuario().setEmail(clienteRequestDTO.email());
        clienteExistente.getUsuario().setLogin(clienteRequestDTO.login());
        clienteExistente.getUsuario().setSenha(clienteRequestDTO.senha());
        clienteExistente.getUsuario().setEndereco(clienteRequestDTO.endereco());
        clienteExistente.getUsuario().setDataUltimaAlteracaoRegistro(LocalDate.now());

        clienteRepository.save(clienteExistente);
    }

    public void deletaCliente(Long id) {
        var clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o id: " + id));

        clienteRepository.delete(clienteExistente);
    }

}
