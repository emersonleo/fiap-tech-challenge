package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IRestauranteApiControllerSwagger {

    @Operation(summary = "Cria um novo restaurante", description = "Adiciona um novo restaurante ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso"),
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
                                                "message": {
                                                    "nomeRestaurante": "O campo 'nomeRestaurante' deve ser preenchido",
                                                    "idProprietario": "O campo 'idProprietario' deve ser preenchido"
                                                },
                                                "statusCode": 400,
                                                "error": "BAD_REQUEST"
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para criação de restaurante",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NovoRestauranteDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Cliente",
                            summary = "Exemplo de payload",
                            value = """
                                    {
                                        "nomeRestaurante": "Mania Caseira",
                                        "endereco": "Av. Conde da Boa Vista, 110",
                                        "tipoCozinha": "Nordestina",
                                        "horarioFuncionamento": "11:00 às 22:00",
                                        "idProprietario": 1,
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<RestauranteDTO> criaRestaurante(@RequestBody NovoRestauranteDTO restaurante);

    @Operation(summary = "Busca todos os restaurantes", description = "Retorna uma lista de restaurantes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de restaurantes retornada com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NovoRestauranteDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Lista de Restaurantes",
                            summary = "Exemplo de resposta para lista de restaurantes",
                            value = """
                                    [
                                         {
                                             "id": 1,
                                             "nomeRestaurante": "Mania Caseira",
                                             "endereco": "Av. Conde da Boa Vista, 110",
                                             "tipoCozinha": "Nordestina",
                                             "horarioFuncionamento": "11:00 às 22:00",
                                             "donoRestaurante": {
                                                 "id": 1,
                                                 "nome": "Carlos Souza",
                                                 "email": "carlos.souza@maniacaseira.com",
                                                 "login": "carlos_maniacaseira",
                                                 "endereco": "Rua das Flores, 123",
                                                 "dataUltimaAlteracao": null
                                             }
                                         },
                                         {
                                             "id": 2,
                                             "nomeRestaurante": "Tradição Grill",
                                             "endereco": "Av. Doutor Belmino Correia, 67",
                                             "tipoCozinha": "Nordestina",
                                             "horarioFuncionamento": "11:00 às 23:00",
                                             "donoRestaurante": {
                                                 "id": 2,
                                                 "nome": "Adriano Santana",
                                                 "email": "adriano.santana@tradicao.com",
                                                 "login": "adriano.donotradicao",
                                                 "endereco": "Rua Tambiar, 34",
                                                 "dataUltimaAlteracao": null
                                             }
                                         }
                                     ]
                                    """
                    )
            ))
    })
    @GetMapping
    ResponseEntity<List<RestauranteDTO>> buscaTodosRestaurantes(@RequestParam int page, @RequestParam int size);

    @Operation(summary = "Busca um restaurante por ID", description = "Retorna os dados de um restaurante específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante retornado com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestauranteDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Restaurantes",
                            summary = "Exemplo de resposta para restaurante encontrado",
                            value = """
                                    {
                                             "id": 2,
                                             "nomeRestaurante": "Tradição Grill",
                                             "endereco": "Av. Doutor Belmino Correia, 67",
                                             "tipoCozinha": "Nordestina",
                                             "horarioFuncionamento": "11:00 às 23:00",
                                             "donoRestaurante": {
                                                 "id": 2,
                                                 "nome": "Adriano Santana",
                                                 "email": "adriano.santana@tradicao.com",
                                                 "login": "adriano.donotradicao",
                                                 "endereco": "Rua Tambiar, 34",
                                                 "dataUltimaAlteracao": null
                                             }
                                         }
                                    """
                    )
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para restaurante não encontrado",
                                    value = """
                                            {
                                                "code": "RESTAURANTE_NOT_FOUND",
                                                "message": "Restaurante não encontrado com o id: 4",
                                                "status": 404
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<RestauranteDTO> buscaRestaurantePorId(@PathVariable Long id);


    @Operation(summary = "Atualiza um restaurante", description = "Atualiza os dados de um restaurante existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para restaurante não encontrado",
                                    value = """
                                            {
                                                "code": "RESTAURANTE_NOT_FOUND",
                                                "message": "Restaurante não encontrado com o id: 10",
                                                "status": 404
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para atualização de restaurante",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RestauranteDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Restaurante",
                            summary = "Exemplo de payload",
                            value = """
                                    {
                                        "nomeRestaurante": "Mania Caseira ALTERADO",
                                        "endereco": "Av. Conde da Boa Vista, 110",
                                        "tipoCozinha": "Nordestina",
                                        "horarioFuncionamento": "11:00 às 22:00",
                                        "idProprietario": 1
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<Void> atualizaRestaurante(@RequestBody AtualizaRestauranteDTO restaurante, @PathVariable("id") Long id);

    @Operation(summary = "Exclui um restaurante", description = "Remove um restaurante do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurante excluído com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurante não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para restaurante não encontrado",
                                    value = """
                                            {
                                                "code": "RESTAURANTE_NOT_FOUND",
                                                "message": "Restaurante não encontrado com o id: 10",
                                                "status": 404
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletaRestaurante(@PathVariable Long id);

}
