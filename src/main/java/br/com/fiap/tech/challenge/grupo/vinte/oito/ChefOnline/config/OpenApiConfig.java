package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
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

}