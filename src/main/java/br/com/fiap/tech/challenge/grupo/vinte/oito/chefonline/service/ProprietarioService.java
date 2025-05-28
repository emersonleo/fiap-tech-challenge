package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ProprietarioResponseDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UpdatePasswordDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository.ProprietarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    private final SenhaService senhaService;

    public ProprietarioService(ProprietarioRepository proprietarioRepository, SenhaService senhaService) {
        this.proprietarioRepository = proprietarioRepository;
        this.senhaService = senhaService;
    }

    public void criaProprietario(ProprietarioRequestDTO proprietarioRequestDTO) {
        Proprietario proprietario = new Proprietario(proprietarioRequestDTO);
        proprietario.getUsuario().setSenha(senhaService.hashSenha(proprietario.getUsuario().getSenha()));
        proprietario.getUsuario().setDataCriacaoRegistro(LocalDate.now());
        proprietarioRepository.save(proprietario);
    }

    public List<ProprietarioResponseDTO> buscaTodosProprietarios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = proprietarioRepository.findAll(pageable);
        return pageResult.getContent()
                .stream()
                .map(ProprietarioResponseDTO::new)
                .toList();
    }

    public ProprietarioResponseDTO buscaProprietarioPorId(Long id) {
        var proprietario = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com o id: " + id));
        return new ProprietarioResponseDTO(proprietario);
    }

    public Optional<Proprietario> buscaProprietarioPorLogin(String login) {
        return proprietarioRepository.findByUsuarioLogin(login);
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

    public void atualizaSenhaProprietario(UpdatePasswordDTO updatePasswordDTO) {
        var proprietarioExistente = proprietarioRepository.findByUsuarioLogin(updatePasswordDTO.login())
                .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com o id: " + updatePasswordDTO.login()));

        proprietarioExistente.getUsuario().setSenha(senhaService.hashSenha(updatePasswordDTO.novaSenha()));
        proprietarioExistente.getUsuario().setDataUltimaAlteracaoRegistro(LocalDate.now());
        proprietarioRepository.save(proprietarioExistente);
    }

}
