package br.com.fiap.tech.challenge.grupo.vinte.oito.horadoremedio.horadoremedio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enfermeiro")
public class EnfermeiroController {

    @GetMapping("/registrar-consulta")
    public String registrarConsultaEnfermeiro() {
        return "Registro de nova consulta (Enfermeiro)";
    }

    @GetMapping("/historico")
    public String getHistoricoEnfermeiro() {
        return "Acesso ao histórico de consultas (Enfermeiro)";
    }
}

