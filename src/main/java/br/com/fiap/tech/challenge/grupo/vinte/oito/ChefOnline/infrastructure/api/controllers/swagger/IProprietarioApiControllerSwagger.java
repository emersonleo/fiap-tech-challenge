package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.controllers.swagger;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.api.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IProprietarioApiControllerSwagger {

    @Operation(summary = "Cria um novo proprietário", description = "Adiciona um novo proprietário ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proprietário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Erro 400",
                            summary = "Erro de validação",
                            value = """
                                    {
                                        "message": {
                                            "nome": "O campo 'nome' deve ser preenchido",
                                            "email": "Digite um e-mail valido",
                                            "cnpj": "O CNPJ deve conter 14 dígitos numéricos"
                                        },
                                        "statusCode": 400,
                                        "error": "BAD_REQUEST"
                                    }
                                    """
                    )
            ))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para atualização de proprietário",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AtualizaProprietarioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Atualização de Proprietário",
                            summary = "Exemplo de payload para atualização",
                            value = """
                                    {
                                        "nome": "Carlos Souza",
                                        "email": "carlos.souza@gmail.com",
                                        "login": "carlos.souza",
                                        "senha": "senha123",
                                        "endereco": "Rua das Palmeiras, 123, Rio de Janeiro, RJ",
                                        "cnpj": "12345678000199",
                                        "razaoSocial": "Carlos Souza ME",
                                        "nomeFantasia": "Carlos Lanches"
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<ProprietarioDTO> criaProprietario(@RequestBody NovoProprietarioDTO novoProprietarioDTO);

    @Operation(summary = "Busca todos os proprietários", description = "Retorna uma lista de proprietários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proprietários retornada com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NovoProprietarioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Lista de Proprietários",
                            summary = "Lista de proprietários",
                            value = """
                                    [
                                        {
                                            "id": 1,
                                            "nome": "Carlos Souza",
                                            "email": "carlos.souza@gmail.com",
                                            "endereco": "Rua das Palmeiras, 123, Rio de Janeiro, RJ",
                                            "cnpj": "12345678000199",
                                            "razaoSocial": "Carlos Souza ME",
                                            "nomeFantasia": "Carlos Lanches"
                                        },
                                        {
                                            "id": 2,
                                            "nome": "Ana Lima",
                                            "email": "ana.lima@gmail.com",
                                            "endereco": "Avenida Paulista, 456, São Paulo, SP",
                                            "cnpj": "98765432000188",
                                            "razaoSocial": "Ana Lima LTDA",
                                            "nomeFantasia": "Ana Doces"
                                        }
                                    ]
                                    """
                    )
            ))
    })
    @GetMapping
    ResponseEntity<List<ProprietarioDTO>> buscaTodosProprietarios(@RequestParam int page, @RequestParam int size);

    @Operation(summary = "Busca um proprietário por ID", description = "Retorna os dados de um proprietário específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Proprietário retornado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProprietarioDTO.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Proprietário",
                                    summary = "Exemplo de resposta para proprietário encontrado",
                                    value = """
                                            {
                                                "id": 1,
                                                "nome": "Carlos Souza",
                                                "email": "carlos.souza@gmail.com",
                                                "endereco": "Rua das Palmeiras, 123, Rio de Janeiro, RJ",
                                                "cnpj": "12345678000199",
                                                "razaoSocial": "Carlos Souza ME",
                                                "nomeFantasia": "Carlos Lanches"
                                            }
                                            """
                            )
                    )),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Erro 404",
                            summary = "Proprietário não encontrado",
                            value = """
                                    {
                                        "message": "Proprietário não encontrado com o id: 1",
                                        "statusCode": 404,
                                        "error": "NOT_FOUND"
                                    }
                                    """
                    )
            ))
    })
    @GetMapping("/{id}")
    ResponseEntity<ProprietarioDTO> buscaProprietarioPorId(@PathVariable Long id);

    @Operation(summary = "Atualiza um proprietário", description = "Atualiza os dados de um proprietário existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proprietário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Erro 404",
                            summary = "Proprietário não encontrado",
                            value = """
                                    {
                                        "message": "Proprietário não encontrado com o id: 1",
                                        "statusCode": 404,
                                        "error": "NOT_FOUND"
                                    }
                                    """
                    )
            ))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para atualização de proprietário",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AtualizaProprietarioDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Atualização de Proprietário",
                            summary = "Exemplo de payload para atualização",
                            value = """
                                    {
                                        "nome": "Carlos Souza",
                                        "email": "carlos.souza@gmail.com",
                                        "endereco": "Rua das Palmeiras, 123, Rio de Janeiro, RJ",
                                        "cnpj": "12345678000199",
                                        "razaoSocial": "Carlos Souza ME",
                                        "nomeFantasia": "Carlos Lanches"
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<Void> atualizaProprietario(@RequestBody AtualizaProprietarioDTO atualizaProprietarioDTO, @PathVariable Long id);

    @Operation(summary = "Exclui um proprietário", description = "Remove um proprietário do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proprietário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Erro 404",
                            summary = "Proprietário não encontrado",
                            value = """
                                    {
                                        "message": "Proprietário não encontrado com o id: 1",
                                        "statusCode": 404,
                                        "error": "NOT_FOUND"
                                    }
                                    """
                    )
            ))
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletaProprietario(@PathVariable Long id);

    @Operation(summary = "Atualiza a senha do proprietário", description = "Permite que o proprietário atualize sua senha")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para atualização de senha",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TrocaSenhaDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Atualização de Senha",
                            summary = "Exemplo de payload para atualizar a senha do proprietário",
                            value = """
                                    {
                                        "login": "carlos.souza",
                                        "novaSenha": "novaSenha123",
                                        "cpfCnpj": "12345678000199"
                                    }
                                    """
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Erro 400",
                            summary = "Erro de validação",
                            value = """
                                    {
                                        "message": {
                                            "login": "O campo 'login' deve ser preenchido",
                                            "novaSenha": "A nova senha deve ter no mínimo 8 caracteres"
                                        },
                                        "statusCode": 400,
                                        "error": "BAD_REQUEST"
                                    }
                                    """
                    )
            )),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Erro 404",
                            summary = "Proprietário não encontrado",
                            value = """
                                    {
                                        "message": "Proprietário não encontrado com o login: carlos.souza",
                                        "statusCode": 404,
                                        "error": "NOT_FOUND"
                                    }
                                    """
                    )
            ))
    })
    @PatchMapping
    ResponseEntity<ProprietarioDTO> atualizaSenhaProprietario(@RequestBody TrocaSenhaDTO trocaSenhaDTO);

}
