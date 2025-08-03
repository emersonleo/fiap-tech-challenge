package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.ProprietarioDTO;

public record RestauranteDTO(
        Long id,
        String nomeRestaurante,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        ProprietarioDTO donoRestaurante

) {
}