package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @GetMapping("/historico")
    public String getHistoricoMedico() {
        return "Acesso ao histórico de consultas (Médico)";
    }

    @GetMapping("/editar")
    public String editarHistoricoMedico() {
        return "Edição do histórico de consultas (Médico)";
    }
}

