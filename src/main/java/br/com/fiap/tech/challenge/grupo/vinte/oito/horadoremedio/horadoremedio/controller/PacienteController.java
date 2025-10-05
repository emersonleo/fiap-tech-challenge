package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @GetMapping("/minhas-consultas")
    public String getMinhasConsultasPaciente() {
        return "Visualização das minhas consultas (Paciente)";
    }
}

