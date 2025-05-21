package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioResponseDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository.ProprietarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public void criaProprietario(ProprietarioRequestDTO proprietarioRequestDTO) {
        Proprietario proprietario = new Proprietario(proprietarioRequestDTO);
        proprietario.getUsuario().setDataCriacaoRegistro(LocalDate.now());
        var save = proprietarioRepository.save(proprietario);
//        Assert.state(save == 1, "Erro ao criar usuario: " + clienteDTO.nome());
    }

    public List<ProprietarioResponseDTO> buscaTodosProprietarios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = proprietarioRepository.findAll(pageable);
        return pageResult.getContent()
                .stream()
                .map(ProprietarioResponseDTO::new)
                .toList();
    }

    public void atualizaProprietario(ProprietarioRequestDTO proprietarioRequestDTO, Long id) {
        var proprietarioExistente = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com o id: " + id));

        proprietarioExistente.getUsuario().setNome(proprietarioRequestDTO.nome());
        proprietarioExistente.getUsuario().setEmail(proprietarioRequestDTO.email());
        proprietarioExistente.getUsuario().setLogin(proprietarioRequestDTO.login());
        proprietarioExistente.getUsuario().setSenha(proprietarioRequestDTO.senha());
        proprietarioExistente.getUsuario().setEndereco(proprietarioRequestDTO.endereco());
        proprietarioExistente.setCnpj(proprietarioRequestDTO.cnpj());
        proprietarioExistente.setRazaoSocial(proprietarioRequestDTO.razaoSocial());
        proprietarioExistente.setNomeFantasia(proprietarioRequestDTO.nomeFantasia());
        proprietarioExistente.getUsuario().setDataUltimaAlteracaoRegistro(LocalDate.now());

        proprietarioRepository.save(proprietarioExistente);
    }

    public void deletaProprietario(Long id) {
        var proprietarioExistente = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com o id: " + id));

        proprietarioRepository.delete(proprietarioExistente);
    }

}
