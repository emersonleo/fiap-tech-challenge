package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller.swagger;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.controller.handlers.ErrorResponse;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteRequestDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.ClienteResponseDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.dto.UpdatePasswordDTO;
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

public interface ClienteControllerSwagger {

    @Operation(summary = "Cria um novo cliente", description = "Adiciona um novo cliente ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
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
                                                    "nome": "O campo 'nome' deve ser preenchido",
                                                    "cpf": "O CPF deve conter 11 dígitos numéricos"
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
            description = "Exemplo de payload para criação de cliente",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteRequestDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Cliente",
                            summary = "Exemplo de payload",
                            value = """
                                    {
                                        "nome": "João Silva",
                                        "cpf": "12345678901",
                                        "email": "joao.silva@gmail.com",
                                        "login": "joaosilva",
                                        "senha": "senhaSegura123",
                                        "idade": 30,
                                        "endereco": "Avenida Caxangá, 123, Recife, PE"
                                    }
                                    """
                    )
            )
    )
    @PostMapping
    ResponseEntity<Void> criaCliente(@RequestBody ClienteRequestDTO cliente);

    @Operation(summary = "Busca todos os clientes", description = "Retorna uma lista de clientes cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteResponseDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Lista de Clientes",
                            summary = "Exemplo de resposta para lista de clientes",
                            value = """
                                    [
                                        {
                                            "id": 1,
                                            "nome": "João Silva",
                                            "cpf": "12345678901",
                                            "email": "joao.silva@gmail.com",
                                            "idade": 30,
                                            "endereco": "Avenida Caxangá, 123, Recife, PE"
                                        },
                                        {
                                            "id": 2,
                                            "nome": "Maria Oliveira",
                                            "cpf": "98765432100",
                                            "email": "maria.oliveira@gmail.com",
                                            "idade": 25,
                                            "endereco": "Rua das Flores, 456, São Paulo, SP"
                                        }
                                    ]
                                    """
                    )
            ))
    })
    @GetMapping
    ResponseEntity<List<ClienteResponseDTO>> buscaTodosClientes(@RequestParam int page, @RequestParam int size);

    @Operation(summary = "Busca um cliente por ID", description = "Retorna os dados de um cliente específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteResponseDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Cliente",
                            summary = "Exemplo de resposta para cliente encontrado",
                            value = """
                                    {
                                        "id": 1,
                                        "nome": "João Silva",
                                        "cpf": "12345678901",
                                        "email": "joao.silva@gmail.com",
                                        "idade": 30,
                                        "endereco": "Avenida Caxangá, 123, Recife, PE"
                                    }
                                    """
                    )
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para cliente não encontrado",
                                    value = """
                                            {
                                                "message": "Cliente não encontrado com o id: 1",
                                                "statusCode": 404,
                                                "error": "NOT_FOUND"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<ClienteResponseDTO> buscaClientePorId(@PathVariable Long id);


    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados de um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para cliente não encontrado",
                                    value = """
                                            {
                                                "message": "Cliente não encontrado com o id: 1",
                                                "statusCode": 404,
                                                "error": "NOT_FOUND"
                                            }
                                            """
                            )
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Exemplo de payload para criação de cliente",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteRequestDTO.class),
                    examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                            name = "Exemplo de Cliente",
                            summary = "Exemplo de payload",
                            value = """
                                    {
                                        "nome": "João Silva",
                                        "cpf": "12345678901",
                                        "email": "joao.silva@gmail.com",
                                        "login": "joaosilva",
                                        "senha": "senhaSegura123",
                                        "idade": 30,
                                        "endereco": "Avenida Caxangá, 123, Recife, PE"
                                    }
                                    """
                    )
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<Void> atualizaCliente(@RequestBody ClienteRequestDTO cliente, @PathVariable("id") Long id);

    @Operation(summary = "Exclui um cliente", description = "Remove um cliente do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "Exemplo de Erro 404",
                                    summary = "Exemplo de resposta para cliente não encontrado",
                                    value = """
                                            {
                                                "message": "Cliente não encontrado com o id: 1",
                                                "statusCode": 404,
                                                "error": "NOT_FOUND"
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletaCliente(@PathVariable Long id);

    @Operation(summary = "Atualiza a senha do cliente", description = "Permite que o cliente atualize sua senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @PatchMapping
    ResponseEntity<Void> atualizaSenha(@RequestBody UpdatePasswordDTO updatePasswordDTO);
}