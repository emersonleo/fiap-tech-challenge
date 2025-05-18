package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<List<Object>> findAllVeiculos() {
        return ResponseEntity.ok(List.of(1, 2, 3));
    }

}
