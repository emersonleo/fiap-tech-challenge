package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioDTO;
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

    public void criaProprietario(ProprietarioDTO proprietarioDTO) {
        Proprietario proprietario = new Proprietario(proprietarioDTO);
        proprietario.setDataCriacaoRegistro(LocalDate.now());
        var save = proprietarioRepository.save(proprietario);
//        Assert.state(save == 1, "Erro ao criar usuario: " + clienteDTO.nome());
    }

    public List<Proprietario> buscaTodosProprietarios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = proprietarioRepository.findAll(pageable);
        return pageResult.getContent();
    }

    public void atualizaProprietario(ProprietarioDTO proprietarioDTO, Long id) {
        var proprietarioExistente = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com o id: " + id));

        proprietarioExistente.setNome(proprietarioDTO.nome());
        proprietarioExistente.setEmail(proprietarioDTO.email());
        proprietarioExistente.setLogin(proprietarioDTO.login());
        proprietarioExistente.setSenha(proprietarioDTO.senha());
        proprietarioExistente.setEndereco(proprietarioDTO.endereco());
        proprietarioExistente.setCnpj(proprietarioDTO.cnpj());
        proprietarioExistente.setRazaoSocial(proprietarioDTO.razaoSocial());
        proprietarioExistente.setNomeFantasia(proprietarioDTO.nomeFantasia());
        proprietarioExistente.setDataUltimaAlteracaoRegistro(LocalDate.now());

        proprietarioRepository.save(proprietarioExistente);
    }

    public void deletaProprietario(Long id) {
        var proprietarioExistente = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com o id: " + id));

        proprietarioRepository.delete(proprietarioExistente);
    }

}
