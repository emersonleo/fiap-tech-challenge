package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.AtualizaItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.ItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.cardapio.NovoItemCardapioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Itens do Cardápio", description = "API para gerenciamento dos itens do cardápio de um restaurante")
public interface IItemCardapioApiControllerSwagger {

    @Operation(summary = "Cria um novo item no cardápio", description = "Adiciona um novo item de cardápio ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de cardápio criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 400",
                                    summary = "Exemplo de resposta para erro de validação",
                                    value = """
                                            {
                                                "code": "MALFORMED_REQUEST",
                                                "message": "Dados de entrada inválidos",
                                                "details": {
                                                    "preco": "Preço é obrigatório",
                                                    "nome": "O nome é obrigatório"
                                                },
                                                "status": 400
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para criação de Item do Cardápio",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NovoItemCardapioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Item de Cardápio",
                            summary = "Exemplo de payload",
                            value = """
                                    {
                                        "nome": "Parmegiana de Frango",
                                        "descricao": "frango empanado com queijo com spaghetti no molho ao sugo",
                                        "preco": 38.90,
                                        "disponibilidadeConsumo": [
                                            "LOCAL",
                                            "DELIVERY",
                                            "RETIRADA"
                                        ],
                                        "foto": "/Path/Cardapio/Foto/parmegiana",
                                        "idRestaurante": 2
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<ItemCardapioDTO> criarItemCardapio(@RequestBody NovoItemCardapioDTO novoItemCardapioDTO);

    @Operation(summary = "Busca todos os Itens de cardápio", description = "Retorna uma lista de Itens de cardápios cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens do cardápio retornada com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NovoItemCardapioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Lista de itens de cardápio",
                            summary = "Exemplo de resposta para lista de itens de cardápio",
                            value = """
                                    [
                                          {
                                              "id": 9,
                                              "nome": "Parmegiana de Frango",
                                              "descricao": "frango empanado com queijo com spaghetti no molho ao sugoooooooooo",
                                              "preco": 38.9,
                                              "disponibilidadeConsumo": [
                                                  "LOCAL",
                                                  "DELIVERY",
                                                  "RETIRADA"
                                              ],
                                              "foto": "/Path/Cardapio/Foto/parmegianaaaaaa",
                                              "restaurante": {
                                                  "id": 3,
                                                  "nomeRestaurante": "Mania Caseira 2",
                                                  "endereco": "Av. Conde da Boa Vista, 110",
                                                  "tipoCozinha": "Nordestina",
                                                  "horarioFuncionamento": "11:00 às 22:00",
                                                  "donoRestaurante": {
                                                      "id": 3,
                                                      "nome": "Carlos Souza",
                                                      "email": "c...o@saborcaseiro.com",
                                                      "login": "carlos.sabor",
                                                      "endereco": "Av...00",
                                                      "dataUltimaAlteracao": null
                                                  }
                                              }
                                          },
                                          {
                                              "id": 10,
                                              "nome": "Costela no Bafo",
                                              "descricao": "Costela suína, feijão macassar, arroz branco e vinagrete",
                                              "preco": 44.9,
                                              "disponibilidadeConsumo": [
                                                  "LOCAL"
                                              ],
                                              "foto": "/Path/Cardapio/Foto/costela-no-bafo",
                                              "restaurante": {
                                                  "id": 2,
                                                  "nomeRestaurante": "Mania Caseira",
                                                  "endereco": "Av. Conde da Boa Vista, 110",
                                                  "tipoCozinha": "Nordestina",
                                                  "horarioFuncionamento": "11:00 às 22:00",
                                                  "donoRestaurante": {
                                                      "id": 3,
                                                      "nome": "Carlos Souza",
                                                      "email": "c...o@saborcaseiro.com",
                                                      "login": "carlos.sabor",
                                                      "endereco": "Av...00",
                                                      "dataUltimaAlteracao": null
                                                  }
                                              }
                                          }
                                      ]
                                    """
                    )
            ))
    })
    @GetMapping
    ResponseEntity<List<ItemCardapioDTO>> buscarTodosItensCardapio(@RequestParam int page, @RequestParam int size);

    @Operation(summary = "Busca um item de cardápio por ID", description = "Retorna os dados de um item de cardápio específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de cardápio retornado com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ItemCardapioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Itens de cardápio",
                            summary = "Exemplo de resposta para itens de cardápio encontrado",
                            value = """
                                    {
                                              "id": 10,
                                              "nome": "Costela no Bafo",
                                              "descricao": "Costela suína, feijão macassar, arroz branco e vinagrete",
                                              "preco": 44.9,
                                              "disponibilidadeConsumo": [
                                                  "LOCAL"
                                              ],
                                              "foto": "/Path/Cardapio/Foto/costela-no-bafo",
                                              "restaurante": {
                                                  "id": 2,
                                                  "nomeRestaurante": "Mania Caseira",
                                                  "endereco": "Av. Conde da Boa Vista, 110",
                                                  "tipoCozinha": "Nordestina",
                                                  "horarioFuncionamento": "11:00 às 22:00",
                                                  "donoRestaurante": {
                                                      "id": 3,
                                                      "nome": "Carlos Souza",
                                                      "email": "c...o@saborcaseiro.com",
                                                      "login": "carlos.sabor",
                                                      "endereco": "Av...00",
                                                      "dataUltimaAlteracao": null
                                                  }
                                              }
                                          }
                                    """
                    )
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item de cardápio não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para item de cardápio não encontrado",
                                    value = """
                                            {
                                                "code": "ITEM_CARDAPIO_NOT_FOUND",
                                                "message": "Item Cardápio não encontrado com o id: 19",
                                                "status": 404
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<ItemCardapioDTO> buscarItemCardapioPorId(@PathVariable Long id);


    @Operation(summary = "Atualiza um item de cardápio", description = "Atualiza os dados de um item de cardápio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de cardápio atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item de cardápio não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para restaurante não encontrado",
                                    value = """
                                            {
                                                "code": "ITEM_CARDAPIO_NOT_FOUND",
                                                "message": "Item Cardápio não encontrado com o id: 19",
                                                "status": 404
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para atualização de item de cardápio",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ItemCardapioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Restaurante",
                            summary = "Exemplo de payload",
                            value = """
                                    {
                                        "nome": "Parmegiana de Frango ALTERADO",
                                        "descricao": "frango empanado com queijo com spaghetti no molho ao sugo ALTERADO",
                                        "preco": 38.90,
                                        "disponibilidadeConsumo": [
                                            "LOCAL",
                                            "DELIVERY",
                                            "RETIRADA"
                                        ],
                                        "foto": "/Path/Cardapio/Foto/parmegiana/ALTERADO",
                                        "idRestaurante": 3
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<Void> atualizarItemCardapio(@RequestBody AtualizaItemCardapioDTO itemCardapioDTO, @PathVariable("id") Long itemId);

    @Operation(summary = "Exclui um item de cardápio", description = "Remove um item de cardápio do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item de cardápio excluído com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Item de cardápio não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para item de cardápio não encontrado",
                                    value = """
                                            {
                                                "code": "ITEM_CARDAPIO_NOT_FOUND",
                                                "message": "Item Cardápio não encontrado com o id: 9",
                                                "status": 404
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarItemCardapio(@PathVariable Long id);

}
