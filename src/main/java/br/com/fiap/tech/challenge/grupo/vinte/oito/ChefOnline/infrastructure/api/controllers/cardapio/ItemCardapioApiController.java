package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.cardapio;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurantes/{id}/cardapio")
@Tag(name = "Cardapio", description = "API para gerenciamento do cardapio de um restaurante")
public class ItemCardapioApiController {
}