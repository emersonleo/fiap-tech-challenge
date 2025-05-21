package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.service;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.LoginRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoLoginService {

    private final ProprietarioService proprietarioService;
    private final ClienteService clienteService;

    public AutenticacaoLoginService(ProprietarioService proprietarioService, ClienteService clienteService) {
        this.proprietarioService = proprietarioService;
        this.clienteService = clienteService;
    }

    public boolean validaLogin(LoginRequestDTO loginRequestDTO){
        if (clienteService.validaLogin(loginRequestDTO)) {
            return true;
        }
        return proprietarioService.validaLogin(loginRequestDTO);
    }

}
