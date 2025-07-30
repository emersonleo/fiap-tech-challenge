package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ChefOnline API")
                        .description("Sistema para gerenciamento de restaurantes")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Grupo 28")
                                .email("grupo28@fiap.com.br"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }

    @Bean
    public OpenApiCustomizer globalResponseCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> {
                pathItem.readOperations().forEach(operation -> {
                    ApiResponses responses = operation.getResponses();
                    
                    // Adiciona respostas globais automaticamente se não existirem
                    if (!responses.containsKey("400")) {
                        responses.addApiResponse("400", new ApiResponse().description("Dados inválidos fornecidos"));
                    }
                    if (!responses.containsKey("404")) {
                        responses.addApiResponse("404", new ApiResponse().description("Recurso não encontrado"));
                    }
                    if (!responses.containsKey("409")) {
                        responses.addApiResponse("409", new ApiResponse().description("Recurso já existe"));
                    }
                    if (!responses.containsKey("500")) {
                        responses.addApiResponse("500", new ApiResponse().description("Erro interno do servidor"));
                    }
                });
            });
        };
    }
}